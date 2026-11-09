package hospital.referral;

import java.util.List;

public final class Referral {

    private final String patient;
    private final List<Examination> examinations;
    private final Urgency urgency;
    private final boolean fasting;
    private final boolean contrast;
    private final List<String> notes;
    private final String doctorSignature;

    Referral(String patient, List<Examination> examinations, Urgency urgency,boolean fasting, boolean contrast, List<String> notes, String doctorSignature) {
        this.patient = patient;
        this.examinations = List.copyOf(examinations);
        this.urgency = urgency;
        this.fasting = fasting;
        this.contrast = contrast;
        this.notes = List.copyOf(notes);
        this.doctorSignature = doctorSignature;
    }

    public String getPatient() {
        return patient;
    }

    public List<Examination> getExaminations() {
        return examinations;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public boolean isFasting() {
        return fasting;
    }

    public boolean isContrast() {
        return contrast;
    }

    public List<String> getNotes() {
        return notes;
    }

    public String getDoctorSignature() {
        return doctorSignature;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Направлення на обстеження\n");
        sb.append("Пацієнт: ").append(patient).append('\n');
        sb.append("Дослідження:\n");
        for (Examination e : examinations) {
            sb.append("  - ").append(e).append('\n');
        }
        sb.append("Терміновість: ").append(urgency.label()).append('\n');
        sb.append("Натщесерце: ").append(fasting ? "так" : "ні").append('\n');
        sb.append("Контраст: ").append(contrast ? "потрібен" : "не потрібен").append('\n');
        sb.append("Примітки: ").append(notes.isEmpty() ? "—" : String.join("; ", notes)).append('\n');
        sb.append("Підпис лікаря: ").append(doctorSignature);
        return sb.toString();
    }
}
