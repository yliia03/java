package task3_flyweight;

public class Prescription {
    private final String patientName;
    private final String dosage;
    private final String intakeSchedule;
    private final MedicationType medication;

    public Prescription(String patientName, String dosage, String intakeSchedule, MedicationType medication) {
        this.patientName = patientName;
        this.dosage = dosage;
        this.intakeSchedule = intakeSchedule;
        this.medication = medication;
    }

    public MedicationType getMedication() {
        return medication;
    }

    public String describe() {
        return String.format("Пацієнт: %s | Доза: %s (%s) | Препарат: %s | %s",
                patientName, dosage, intakeSchedule,
                medication.getShortInfo(), medication.getFullClinicalDetails());
    }
}
