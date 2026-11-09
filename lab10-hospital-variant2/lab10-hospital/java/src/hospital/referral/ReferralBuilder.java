package hospital.referral;

public interface ReferralBuilder {

    ReferralBuilder forPatient(String patientName);

    ReferralBuilder addAnalysis(String name);

    ReferralBuilder addProcedure(String name);

    ReferralBuilder signedBy(String doctor);

    ReferralBuilder urgency(Urgency urgency);

    ReferralBuilder fasting();

    ReferralBuilder withContrast();

    ReferralBuilder note(String text);

    /**
     * Завершальний крок: перевірка обов'язкових частин і створення об'єкта.
     *
     * @throws ReferralValidationException якщо направлення неповне
     */
    Referral build();
}
