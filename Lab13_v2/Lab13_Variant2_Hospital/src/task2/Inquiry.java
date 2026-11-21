package task2;

public class Inquiry {
    public final int id;
    public final String caller;
    public final InquiryType type;
    public final String text;

    public Inquiry(int id, String caller, InquiryType type, String text) {
        this.id = id;
        this.caller = caller;
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("#%d від '%s' [%s]: \"%s\"", id, caller, type.title(), text);
    }
}
