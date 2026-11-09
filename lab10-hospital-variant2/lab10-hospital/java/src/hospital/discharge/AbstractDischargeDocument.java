package hospital.discharge;

public abstract class AbstractDischargeDocument implements DischargeDocument {

    protected final Patient patient;
    private String signedBy;

    protected AbstractDischargeDocument(Patient patient) {
        this.patient = patient;
    }

    protected abstract String specificSection();

    @Override
    public String patientName() {
        return patient.getName();
    }

    @Override
    public void sign(String doctor) {
        this.signedBy = doctor;
    }

    @Override
    public boolean isSigned() {
        return signedBy != null;
    }

    @Override
    public String content() {
        return "Документ: " + title() + "\n"+ "Пацієнт: " + patient.getName() + "\n"+ "Діагноз: " + patient.getDiagnosis() + "\n" + specificSection() + "\n"+ "Підпис: " + (signedBy == null ? "—" : signedBy);
    }
}
