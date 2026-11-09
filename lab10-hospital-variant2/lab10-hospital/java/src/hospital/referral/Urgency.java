package hospital.referral;

public enum Urgency {
    PLANNED("планове"),
    URGENT("ТЕРМІНОВО");

    private final String label;

    Urgency(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
