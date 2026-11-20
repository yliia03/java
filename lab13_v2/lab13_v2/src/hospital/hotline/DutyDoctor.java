package hospital.hotline;

import hospital.common.EventLog;

public class DutyDoctor extends InquiryHandler {
    public DutyDoctor(String name, EventLog log) {
        super(name, log);
    }

    public DutyDoctor(EventLog log) {
        this("Черговий лікар", log);
    }

    @Override
    protected boolean canHandle(Inquiry inquiry) {
        return inquiry.type() == InquiryType.URGENT_MEDICAL || inquiry.type() == InquiryType.TREATMENT;
    }

    @Override
    protected void process(Inquiry inquiry) {
        log.log(name, "ОБРОБИВ звернення #" + inquiry.id() + ": дав медичну консультацію");
    }
}
