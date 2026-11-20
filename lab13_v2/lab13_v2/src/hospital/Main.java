package hospital;

import hospital.command.*;
import hospital.common.EventLog;
import hospital.hotline.*;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        runPrescriptions();
        runHotline();
    }

    static void runPrescriptions() {
        EventLog log = new EventLog(LocalTime.of(9, 0));
        log.section("Завдання 1. Призначення лікаря (Command)");

        PatientChart chart = new PatientChart("Коваленко");
        chart.addMedication("Парацетамол", 500);
        chart.addProcedure("Крапельниця глюкози");
        System.out.println("   Початковий стан: " + chart);

        OrderQueue queue = new OrderQueue();
        Doctor doctor = new Doctor("Мельник", queue, log);
        Nurse nurse = new Nurse("Іваненко", queue, log);

        doctor.prescribe(new ChangeDoseOrder(chart, "Парацетамол", 250));
        doctor.prescribe(new CancelProcedureOrder(chart, "Крапельниця глюкози"));
        doctor.prescribe(new AddMedicationOrder(chart, "Морфін", 10)); // помилкове
        doctor.printQueue();

        doctor.revokeLast();
        doctor.printQueue();

        log.advanceMinutes(90);
        nurse.executeAll();
        nurse.printJournal();
        System.out.println("   Підсумковий стан: " + chart);
    }

    static void runHotline() {
        EventLog log = new EventLog(LocalTime.of(10, 0));
        log.section("Завдання 2. Гаряча лінія (Chain of Responsibility)");

        InquiryHandler operator = new DutyOperator(log);
        InquiryHandler nurse = new NurseHandler(log);
        InquiryHandler doctor = new DutyDoctor(log);
        InquiryHandler emergency = new EmergencyService(log);

        HotlineService hotline = new HotlineService(log);

        hotline.configure("денна зміна", List.of(operator, nurse, doctor, emergency));
        hotline.submit(new Inquiry(1, "Пацієнт Бондар", InquiryType.GENERAL, "Які години прийому в терапії?"));
        hotline.submit(new Inquiry(2, "Пацієнтка Шевчук", InquiryType.TREATMENT, "Як приймати антибіотик після їжі чи до?"));
        hotline.submit(new Inquiry(3, "Пацієнт Гнатюк", InquiryType.URGENT_MEDICAL, "Тиск 200/120, сильний головний біль"));
        hotline.submit(new Inquiry(4, "Пацієнт Лис", InquiryType.LIFE_THREAT, "Людина без свідомості, не дихає"));

        log.section("Нічний режим: ланцюг без медсестри");
        hotline.configure("нічна зміна", List.of(operator, doctor, emergency));
        hotline.submit(new Inquiry(5, "Пацієнтка Мороз", InquiryType.TREATMENT, "Чи можна поєднувати ці два препарати?"));

        log.section("Звернення поза компетенцією");
        hotline.submit(new Inquiry(6, "Відвідувач Кравець", InquiryType.OTHER, "Де залишити авто на платній парковці?"));
    }
}
