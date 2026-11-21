package task2;

public class NurseHandler extends InquiryHandler {
    public NurseHandler(String name, EventLog log) {
        super(name, log);
    }

    @Override
    protected boolean canHandle(Inquiry inquiry) {
        return inquiry.type == InquiryType.TREATMENT;
    }

    @Override
    protected void process(Inquiry inquiry) {
        log.log(name, "Оброблено звернення щодо догляду " + inquiry + ". Надано рекомендації щодо прийому ліків.");
    }
}
