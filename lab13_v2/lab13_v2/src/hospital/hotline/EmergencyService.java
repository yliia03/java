package hospital.hotline;

import hospital.common.EventLog;

public class EmergencyService extends InquiryHandler {
    public EmergencyService(String name, EventLog log) {
        super(name, log);
    }

    public EmergencyService(EventLog log) {
        this("Екстрена служба", log);
    }

    @Override
    protected boolean canHandle(Inquiry inquiry) {
        return inquiry.type() == InquiryType.LIFE_THREAT;
    }

    @Override
    protected void process(Inquiry inquiry) {
        log.log(name, "ОБРОБИВ звернення #" + inquiry.id() + ": виїзд бригади, звернення передано в екстрену службу");
    }
}
