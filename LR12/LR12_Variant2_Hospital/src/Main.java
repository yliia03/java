import task1_bridge.*;
import task2_composite.*;
import task3_flyweight.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        runTask1Bridge();
        System.out.println("\n" + "=".repeat(75) + "\n");
        runTask2Composite();
        System.out.println("\n" + "=".repeat(75) + "\n");
        runTask3Flyweight();
    }

    public static void runTask1Bridge() {
        EventLogger.log("Task1-Bridge", "Старт демонстрації формування медичних звітів");

        ReportFormat print = new PrintFormat();
        ReportFormat pdf = new PdfFormat();
        ReportFormat gov = new ElectronicGovFormat();

        MedicalReport discharge = new PatientDischargeReport(print, "Коваленко І.П.", "Гострий бронхіт");
        EventLogger.log("Bridge", "Виписка -> " + print.getFormatName() + ": " + discharge.generate());

        discharge.setFormat(pdf);
        EventLogger.log("Bridge", "Виписка -> " + pdf.getFormatName() + ": " + discharge.generate());

        MedicalReport stat = new HealthDepartmentStatReport(gov, "Q3 2026", "Госпіталізацій: 1420; Виписок: 1385");
        EventLogger.log("Bridge", "Статформа -> " + gov.getFormatName() + ": " + stat.generate());

        ReportFormat csv = new CsvFormat();
        MedicalReport deptReport = new DepartmentMonthlyReport(csv, "Кардіологічне", 215);
        EventLogger.log("Bridge", "Додано новий формат CSV одним класом");
        EventLogger.log("Bridge", "Звіт відділення -> " + csv.getFormatName() + ": " + deptReport.generate());
    }

    public static void runTask2Composite() {
        EventLogger.log("Task2-Composite", "Побудова ієрархії ліжкового фонду Обласної лікарні");

        HospitalDivision hospital = new HospitalDivision("Лікарня", "Чернівецька обласна клінічна лікарня");

        HospitalDivision corpus1 = new HospitalDivision("Корпус", "Терапевтичний №1");
        HospitalDivision cardioDept = new HospitalDivision("Відділення", "Кардіологічне");

        HospitalDivision icuBlock = new HospitalDivision("Блок", "Інтенсивної терапії");
        HospitalDivision icuWard = new HospitalDivision("Палата", "БІТ-1");
        icuWard.add(new Bed("101-А", true));
        icuWard.add(new Bed("101-Б", false));
        icuBlock.add(icuWard);

        HospitalDivision ward102 = new HospitalDivision("Палата", "102");
        ward102.add(new Bed("102-А", false));
        ward102.add(new Bed("102-Б", true));
        ward102.add(new Bed("102-В", false));

        cardioDept.add(icuBlock);
        cardioDept.add(ward102);
        corpus1.add(cardioDept);

        HospitalDivision corpus2 = new HospitalDivision("Корпус", "Хірургічний №2");
        HospitalDivision surgeryDept = new HospitalDivision("Відділення", "Загальної хірургії");
        HospitalDivision ward201 = new HospitalDivision("Палата", "201");
        ward201.add(new Bed("201-А", true));
        ward201.add(new Bed("201-Б", true));
        ward201.add(new Bed("201-В", false));
        surgeryDept.add(ward201);
        corpus2.add(surgeryDept);

        hospital.add(corpus1);
        hospital.add(corpus2);

        EventLogger.log("Composite", "Виведення структури лікарні деревом:");
        hospital.printTree("");

        EventLogger.log("Composite", "Вільних ліжок у всій лікарні (уніфікований виклик): " + hospital.getFreeBeds());
        EventLogger.log("Composite", "Вільних ліжок у Кардіологічному відділенні (той самий виклик): " + cardioDept.getFreeBeds());
    }

    public static void runTask3Flyweight() {
        EventLogger.log("Task3-Flyweight", "Генерація 1000 призначень для 300 пацієнтів із довідника з 8 препаратів");

        String[][] catalog = {
            {"Амоксиклав", "Амоксицилін + клавуланова кислота", "Таблетки 875/125 мг"},
            {"Ібупрофен", "Ібупрофен", "Капсули 400 мг"},
            {"Бісопролол", "Бісопролол фумарат", "Таблетки 5 мг"},
            {"Омепразол", "Омепразол", "Капсули 20 мг"},
            {"Цефтріаксон", "Цефтріаксон натрію", "Порошок для ін'єкцій 1 г"},
            {"Еналаприл", "Еналаприл малеат", "Таблетки 10 мг"},
            {"Панкреатин", "Панкреатин", "Таблетки 10000 ОД"},
            {"Лоратадин", "Лоратадин", "Таблетки 10 мг"}
        };

        List<Prescription> prescriptions = new ArrayList<>();
        Random rnd = new Random(42);
        String[] schedules = {"2 рази на день після їжі", "1 раз вранці", "3 рази на день", "Перед сном"};
        String[] dosages = {"1 табл.", "2 табл.", "1 капс.", "1 флакон в/в"};

        for (int i = 0; i < 1000; i++) {
            int patientId = (i % 300) + 1;
            String[] medData = catalog[rnd.nextInt(catalog.length)];

            MedicationType sharedMed = MedicationFactory.getMedication(
                    medData[0], medData[1], medData[2],
                    "Приймати строго за схемою (" + medData[0] + ")",
                    "Гіперчутливість до " + medData[1],
                    "Контроль сумісності з НПЗЗ"
            );

            prescriptions.add(new Prescription(
                    "Пацієнт #" + patientId,
                    dosages[rnd.nextInt(dosages.length)],
                    schedules[rnd.nextInt(schedules.length)],
                    sharedMed
            ));
        }

        EventLogger.log("Flyweight", "Створено об'єктів призначень (Prescription): " + prescriptions.size());
        EventLogger.log("Flyweight", "Фактично створено об'єктів препаратів у пам'яті (MedicationType): "
                + MedicationFactory.getCreatedTypesCount());

        Prescription first = prescriptions.get(0);
        Prescription second = null;
        for (int i = 1; i < prescriptions.size(); i++) {
            if (prescriptions.get(i).getMedication() == first.getMedication()) {
                second = prescriptions.get(i);
                break;
            }
        }

        EventLogger.log("Flyweight", "Приклад 1 -> " + first.describe());
        if (second != null) {
            EventLogger.log("Flyweight", "Приклад 2 -> " + second.describe());
            boolean sameRef = (first.getMedication() == second.getMedication());
            EventLogger.log("Flyweight", "Посилаються на ОДИН І ТОЙ САМИЙ об'єкт препарату в пам'яті (==): " + sameRef);
        }
    }
}
