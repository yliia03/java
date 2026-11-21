package task1;

public class MainTask1 {
    public static void main(String[] args) {
        System.out.println("=== ЗАВДАННЯ 1: ПРИЗНАЧЕННЯ ЛІКАРЯ (ШАБЛОН COMMAND) ===");
        EventLog log = new EventLog();
        OrderQueue queue = new OrderQueue();
        PatientChart chart = new PatientChart("Ковальчук І.П.");

        Doctor doctor = new Doctor("Лікар Д-р Петренко", queue, log);
        Nurse nurse = new Nurse("Медсестра Олена", queue, log);

        chart.printChartState();

        System.out.println();
        System.out.println("--- Крок 1: Лікар формує призначення в чергу ---");
        doctor.prescribe(new AddMedicationOrder(chart, "Цефтріаксон", 1000));
        log.advanceMinutes(5);
        doctor.prescribe(new ChangeDoseOrder(chart, "Цефтріаксон", 2000));
        log.advanceMinutes(5);
        doctor.prescribe(new CancelProcedureOrder(chart, "Рентген грудної клітки"));
        log.advanceMinutes(5);
        doctor.prescribe(new AddMedicationOrder(chart, "Парацетамол", 500));

        System.out.println();
        System.out.println("--- Крок 2: Лікар відкликає останнє невиконане призначення ---");
        log.advanceMinutes(10);
        doctor.revokeLast();
        doctor.printQueue();

        System.out.println();
        System.out.println("--- Крок 3: Медсестра виконує всі призначення з черги ---");
        log.advanceMinutes(20);
        nurse.executeAll();
        chart.printChartState();

        System.out.println();
        System.out.println("--- Крок 4: Скасування (Undo) останньої виконаної дії ---");
        log.advanceMinutes(15);
        nurse.undoLastExecuted();
        chart.printChartState();

        System.out.println();
        nurse.printJournal();
    }
}
