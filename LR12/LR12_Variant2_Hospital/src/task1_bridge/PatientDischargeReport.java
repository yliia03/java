package task1_bridge;

public class PatientDischargeReport extends MedicalReport {
    private final String patientName;
    private final String diagnosis;

    public PatientDischargeReport(ReportFormat format, String patientName, String diagnosis) {
        super(format);
        this.patientName = patientName;
        this.diagnosis = diagnosis;
    }

    @Override
    public String generate() {
        return format.export("Виписка пацієнта: " + patientName, "Діагноз: " + diagnosis);
    }
}
