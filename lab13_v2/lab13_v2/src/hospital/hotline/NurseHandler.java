package hospital.hotline;

import hospital.common.EventLog;

public class NurseHandler extends InquiryHandler {
    public NurseHandler(String name, EventLog log) {
        super(name, log);
    }

    public NurseHandler(EventLog log) {
        this("Медсестра", log);
    }

    @Override
    protected boolean canHandle(Inquiry inquiry) {
        return inquiry.type() == InquiryType.TREATMENT;
    }

    @Override
    protected void process(Inquiry inquiry) {
        log.log(name, "ОБРОБИВ звернення #" + inquiry.id() + ": проконсультувала щодо лікування");
    }
}
