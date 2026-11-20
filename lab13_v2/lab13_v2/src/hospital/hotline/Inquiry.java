package hospital.hotline;

public record Inquiry(int id, String caller, InquiryType type, String text) {
    @Override
    public String toString() {
        return "звернення #" + id + " від " + caller + " [" + type.title() + "]: «" + text + "»";
    }
}
