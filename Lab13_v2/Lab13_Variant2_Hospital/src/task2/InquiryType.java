package task2;

public enum InquiryType {
    GENERAL("Загальна довідка / графік роботи"),
    TREATMENT("Питання щодо поточного лікування та догляду"),
    URGENT_MEDICAL("Гострий стан / невідкладна лікарська консультація"),
    LIFE_THREAT("Критичний стан / загроза життю"),
    OTHER("Нестандартне звернення");

    private final String title;

    InquiryType(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }
}
