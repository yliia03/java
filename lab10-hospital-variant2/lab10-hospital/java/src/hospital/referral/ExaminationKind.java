package hospital.referral;

public enum ExaminationKind {
    ANALYSIS("аналіз"),
    PROCEDURE("процедура");

    private final String label;

    ExaminationKind(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
