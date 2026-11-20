package hospital.hotline;

public enum InquiryType {
    GENERAL("загальне питання"),
    TREATMENT("питання щодо лікування"),
    URGENT_MEDICAL("термінове медичне питання"),
    LIFE_THREAT("загроза життю"),
    OTHER("інше (поза регламентом)");

    private final String title;

    InquiryType(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }
}
