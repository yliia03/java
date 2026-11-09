package hospital;

import hospital.discharge.CardiologyDepartment;
import hospital.discharge.DischargeArchive;
import hospital.discharge.DischargeDocument;
import hospital.discharge.Patient;
import hospital.discharge.PediatricsDepartment;
import hospital.discharge.SurgeryDepartment;
import hospital.discharge.TherapyDepartment;
import hospital.kits.AdultKitFactory;
import hospital.kits.ChildKitFactory;
import hospital.kits.MedicalKit;
import hospital.kits.NewbornKitFactory;
import hospital.kits.Ward;
import hospital.referral.Referral;
import hospital.referral.ReferralBuilder;
import hospital.referral.ReferralDirector;
import hospital.referral.ReferralValidationException;
import hospital.referral.StandardReferralBuilder;
import hospital.referral.Urgency;
import hospital.util.EventLog;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));

        demoDischarge();
        demoReferrals();
        demoMedicalKits();
    }

    static void demoDischarge() {
        EventLog.section("Завдання 1. Factory Method — виписка пацієнта");
        DischargeArchive archive = new DischargeArchive();

        Patient p1 = new Patient("Петренко О.", "Гостра пневмонія", true,
                Map.of("перебіг лікування", "антибіотикотерапія 10 днів, стан задовільний"));
        Patient p2 = new Patient("Шевченко Т.", "Гострий апендицит", true,
                Map.of("протокол операції", "апендектомія лапароскопічна, без ускладнень"));
        Patient p3 = new Patient("Мельник І.", "Стенокардія напруги", true,
                Map.of("ЕКГ", "синусовий ритм, ЧСС 72, ознак ішемії немає"));

        printDocument(new TherapyDepartment(archive).discharge(p1));
        printDocument(new SurgeryDepartment(archive).discharge(p2));
        printDocument(new CardiologyDepartment(archive).discharge(p3));

        EventLog.log("Раннер", "Відкрито педіатричне відділення (нові класи, без змін процедури виписки)");

        Patient p4 = new Patient("Іваненко Софія (6 р.)", "Гострий бронхіт", true,
                Map.of("щеплення", "за календарем, протипоказань немає"));
        printDocument(new PediatricsDepartment(archive).discharge(p4));

        EventLog.log("Раннер", "Усього документів в архіві: " + archive.size());
    }

    private static void printDocument(Optional<DischargeDocument> document) {
        document.ifPresent(d -> EventLog.block(d.content()));
    }

    static void demoReferrals() {
        EventLog.section("Завдання 2. Builder — направлення на обстеження");

        Referral kovalenko = new StandardReferralBuilder()
                .forPatient("Коваленко")
                .addAnalysis("Загальний аналіз крові")
                .addProcedure("УЗД")
                .urgency(Urgency.URGENT)
                .note("терміново")
                .signedBy("Др. Левченко")
                .build();
        EventLog.log("Раннер", "Отримано направлення:");
        EventLog.block(kovalenko.toString());

        ReferralDirector director = new ReferralDirector(new StandardReferralBuilder());
        Referral preop = director.buildPreoperative("Бондаренко", "Др. Левченко");
        EventLog.log("Раннер", "Отримано типове направлення:");
        EventLog.block(preop.toString());

        ReferralBuilder broken = new StandardReferralBuilder()
                .forPatient("Гриценко")
                .signedBy("Др. Левченко");
        try {
            broken.build();
        } catch (ReferralValidationException e) {
            EventLog.log("Раннер", "Об'єкт направлення не створено, у лабораторію нічого не передано");
        }
    }

    static void demoMedicalKits() {
        EventLog.section("Завдання 3. Abstract Factory — набори медичних виробів");
        Ward ward = new Ward("Палата №7");

        MedicalKit adult = ward.prepareKit("Сидоренко (дорослий)", new AdultKitFactory());
        EventLog.block(adult.toString());
        MedicalKit child = ward.prepareKit("Остапчук (дитина)", new ChildKitFactory());
        EventLog.block(child.toString());

        EventLog.log("Раннер", "Додано категорію «новонароджені» (нові класи, без змін у Ward)");
        MedicalKit newborn = ward.prepareKit("Мазур (новонароджений)", new NewbornKitFactory());
        EventLog.block(newborn.toString());
    }
}
