package hospital.hotline;

import hospital.common.EventLog;

public class DutyOperator extends InquiryHandler {
    public DutyOperator(String name, EventLog log) {
        super(name, log);
    }

    public DutyOperator(EventLog log) {
        this("Черговий оператор", log);
    }

    @Override
    protected boolean canHandle(Inquiry inquiry) {
        return inquiry.type() == InquiryType.GENERAL;
    }

    @Override
    protected void process(Inquiry inquiry) {
        log.log(name, "ОБРОБИВ звернення #" + inquiry.id() + ": дав відповідь на загальне питання");
    }
}
