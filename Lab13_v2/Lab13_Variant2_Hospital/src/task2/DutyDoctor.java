package task2;

public class DutyDoctor extends InquiryHandler {
    public DutyDoctor(String name, EventLog log) {
        super(name, log);
    }

    @Override
    protected boolean canHandle(Inquiry inquiry) {
        return inquiry.type == InquiryType.URGENT_MEDICAL;
    }

    @Override
    protected void process(Inquiry inquiry) {
        log.log(name, "Оброблено термінове медичне звернення " + inquiry + ". Призначено огляд та корекцію терапії.");
    }
}
