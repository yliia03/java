package hospital.referral;

public record Examination(ExaminationKind kind, String name) {

    @Override
    public String toString() {
        return "[" + kind.label() + "] " + name;
    }
}
