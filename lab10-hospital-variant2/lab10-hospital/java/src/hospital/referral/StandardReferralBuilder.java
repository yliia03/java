package hospital.referral;

import hospital.util.EventLog;
import java.util.ArrayList;
import java.util.List;

public class StandardReferralBuilder implements ReferralBuilder {

    private static final String SOURCE = "Конструктор направлення";

    private String patient;
    private final List<Examination> examinations = new ArrayList<>();
    private Urgency urgency = Urgency.PLANNED;
    private boolean fasting;
    private boolean contrast;
    private final List<String> notes = new ArrayList<>();
    private String doctor;

    @Override
    public ReferralBuilder forPatient(String patientName) {
        this.patient = patientName;
        EventLog.log(SOURCE, "Задано пацієнта: " + patientName);
        return this;
    }

    @Override
    public ReferralBuilder addAnalysis(String name) {
        examinations.add(new Examination(ExaminationKind.ANALYSIS, name));
        EventLog.log(SOURCE, "Додано аналіз: " + name);
        return this;
    }

    @Override
    public ReferralBuilder addProcedure(String name) {
        examinations.add(new Examination(ExaminationKind.PROCEDURE, name));
        EventLog.log(SOURCE, "Додано процедуру: " + name);
        return this;
    }

    @Override
    public ReferralBuilder signedBy(String doctor) {
        this.doctor = doctor;
        EventLog.log(SOURCE, "Поставлено підпис лікаря: " + doctor);
        return this;
    }

    @Override
    public ReferralBuilder urgency(Urgency urgency) {
        this.urgency = urgency;
        EventLog.log(SOURCE, "Задано терміновість: " + urgency.label());
        return this;
    }

    @Override
    public ReferralBuilder fasting() {
        this.fasting = true;
        EventLog.log(SOURCE, "Вимога: натщесерце");
        return this;
    }

    @Override
    public ReferralBuilder withContrast() {
        this.contrast = true;
        EventLog.log(SOURCE, "Вимога: потрібен контраст");
        return this;
    }

    @Override
    public ReferralBuilder note(String text) {
        notes.add(text);
        EventLog.log(SOURCE, "Додано примітку: " + text);
        return this;
    }

    @Override
    public Referral build() {
        List<String> problems = new ArrayList<>();
        if (patient == null || patient.isBlank()) {
            problems.add("не вказано пацієнта");
        }
        if (examinations.isEmpty()) {
            problems.add("не вказано жодного дослідження");
        }
        if (doctor == null || doctor.isBlank()) {
            problems.add("відсутній підпис лікаря");
        }

        if (!problems.isEmpty()) {
            String message = "Неможливо сформувати направлення: " + String.join("; ", problems);
            EventLog.log(SOURCE, message);
            throw new ReferralValidationException(message);
        }

        Referral referral = new Referral(patient, examinations, urgency, fasting, contrast, notes, doctor);
        EventLog.log(SOURCE, "Направлення сформовано: пацієнт " + patient
                + ", досліджень: " + examinations.size());
        return referral;
    }
}
