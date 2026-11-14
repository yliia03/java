package ua.hospital;

import ua.hospital.admission.AdmissionDepartment;
import ua.hospital.admission.BedRegistry;
import ua.hospital.admission.DoctorAssignment;
import ua.hospital.admission.ExaminationPlanner;
import ua.hospital.admission.HospitalizationFacade;
import ua.hospital.admission.HospitalizationResult;
import ua.hospital.admission.KitchenNotifier;
import ua.hospital.admission.Patient;
import ua.hospital.admission.PatientRegistration;
import ua.hospital.admission.PlannedHospitalizationModule;
import ua.hospital.card.AllergyMark;
import ua.hospital.card.BasicPatientCard;
import ua.hospital.card.FallRiskMark;
import ua.hospital.card.InfectionControlMark;
import ua.hospital.card.PatientCard;
import ua.hospital.common.Log;
import ua.hospital.imaging.Employee;
import ua.hospital.imaging.ImageProxy;
import ua.hospital.imaging.ImageStorage;
import ua.hospital.imaging.MedicalImage;
import ua.hospital.imaging.PatientImageCard;
import ua.hospital.imaging.Role;
import ua.hospital.lab.BioChemDriver;
import ua.hospital.lab.BioChemDriverAdapter;
import ua.hospital.lab.ChemProLink;
import ua.hospital.lab.ChemProLinkAdapter;
import ua.hospital.lab.HospitalLabSystem;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        situation1ProxyImages();
        situation2AdapterAnalyzer();
        situation3FacadeHospitalization();
        situation4DecoratorCardMarks();
    }

    static void situation1ProxyImages() {
        Log.section("Ситуація 1 — Proxy: перегляд медичних знімків");
        ImageStorage storage = new ImageStorage();
        List<MedicalImage> images = List.of(
                new ImageProxy("KT-101", "КТ грудної клітки", 420, storage),
                new ImageProxy("MRI-102", "МРТ головного мозку", 610, storage),
                new ImageProxy("KT-103", "КТ черевної порожнини", 380, storage),
                new ImageProxy("MRI-104", "МРТ хребта", 540, storage));
        PatientImageCard card = new PatientImageCard("Мельник О. І.", images);
        Employee doctor = new Employee("Шевченко", Role.DOCTOR);
        Employee registrar = new Employee("Коваль", Role.REGISTRAR);

        card.show();
        Log.log("Main", "Завантажень зі сховища після відкриття картки: " + storage.getDownloadCount());

        card.openImage("MRI-102", doctor);
        card.openImage("MRI-102", doctor);
        Log.log("Main", "Завантажень зі сховища після двох відкриттів МРТ: " + storage.getDownloadCount());

        card.openImage("MRI-102", registrar);
    }

    static void situation2AdapterAnalyzer() {
        Log.section("Ситуація 2 — Adapter: підключення лабораторного аналізатора");
        HospitalLabSystem hospital = new HospitalLabSystem();

        Log.log("Main", "Крок 1: сирий результат драйвера у форматі виробника");
        BioChemDriver driver = new BioChemDriver();
        driver.requestRawResult("P-001");

        Log.log("Main", "Крок 2: система лікарні отримує той самий результат через адаптер");
        hospital.receive(new BioChemDriverAdapter(driver), "P-001");

        Log.log("Main", "Крок 3: другий аналізатор з іншим форматом, система лікарні не змінюється");
        hospital.receive(new ChemProLinkAdapter(new ChemProLink()), "P-002");
    }

    static void situation3FacadeHospitalization() {
        Log.section("Ситуація 3 — Facade: госпіталізація пацієнта");
        BedRegistry beds = new BedRegistry();
        HospitalizationFacade facade = new HospitalizationFacade(
                new PatientRegistration(), beds, new DoctorAssignment(),
                new ExaminationPlanner(), new KitchenNotifier());
        AdmissionDepartment admission = new AdmissionDepartment(facade);
        PlannedHospitalizationModule planned = new PlannedHospitalizationModule(facade);

        Log.log("Main", "Крок 1: екстрена госпіталізація через приймальне відділення");
        HospitalizationResult first = admission.admitEmergency(
                new Patient("Гончар І. П.", "гострий коронарний синдром", "Кардіологія", "стіл №10"));

        Log.log("Main", "Крок 2: планова госпіталізація тією самою операцією");
        planned.admitPlanned(
                new Patient("Лисенко Т. В.", "планове обстеження", "Неврологія", "стіл №5"));

        Log.log("Main", "Крок 3: переведення в іншу палату без повторної госпіталізації (підсистема напряму)");
        beds.transfer(first.patientId(), "Палата 305 (Кардіологія)");
    }

    static void situation4DecoratorCardMarks() {
        Log.section("Ситуація 4 — Decorator: позначки в картці пацієнта");

        Log.log("Main", "Крок 1: звичайна картка");
        PatientCard plain = new BasicPatientCard("Ткачук Н. О.", "палата 210");
        Log.log("Main", plain.render());

        Log.log("Main", "Крок 2: додано позначки «алергія» та «ризик падіння»");
        PatientCard marked = new FallRiskMark(new AllergyMark(plain, "пеніцилін"));
        Log.log("Main", marked.render());

        Log.log("Main", "Крок 3: іншому пацієнту додано «інфекційний контроль», призначаємо процедуру");
        PatientCard infectious = new InfectionControlMark(new BasicPatientCard("Руденко В. М.", "палата 214"));
        infectious.assignProcedure("внутрішньовенна інфузія");
    }
}
