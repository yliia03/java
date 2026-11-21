package task2;

public class DutyOperator extends InquiryHandler {
    public DutyOperator(String name, EventLog log) {
        super(name, log);
    }

    @Override
    protected boolean canHandle(Inquiry inquiry) {
        return inquiry.type == InquiryType.GENERAL;
    }

    @Override
    protected void process(Inquiry inquiry) {
        log.log(name, "Оброблено довідкове звернення " + inquiry + ". Надано інформацію про графік лікарів.");
    }
}
