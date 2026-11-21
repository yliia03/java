package task2;

public class EmergencyService extends InquiryHandler {
    public EmergencyService(String name, EventLog log) {
        super(name, log);
    }

    @Override
    protected boolean canHandle(Inquiry inquiry) {
        return inquiry.type == InquiryType.LIFE_THREAT;
    }

    @Override
    protected void process(Inquiry inquiry) {
        log.log(name, "ЕКСТРЕНИЙ ВИЇЗД! Бригада швидкої допомоги виїхала на виклик " + inquiry);
    }
}
