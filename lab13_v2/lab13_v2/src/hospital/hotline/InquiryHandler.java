package hospital.hotline;

import hospital.common.EventLog;

public abstract class InquiryHandler {
    private InquiryHandler next;
    protected final String name;
    protected final EventLog log;

    protected InquiryHandler(String name, EventLog log) {
        this.name = name;
        this.log = log;
    }

    public String getName() {
        return name;
    }

    public void setNext(InquiryHandler next) {
        this.next = next;
    }

    public final boolean handle(Inquiry inquiry) {
        log.log(name, "отримав " + inquiry);
        if (canHandle(inquiry)) {
            process(inquiry);
            return true;
        }
        if (next == null) {
            log.log(name, "кінець ланцюга: передавати далі нікому");
            return false;
        }
        log.log(name, "не в моїй компетенції → передаю далі: " + next.getName());
        return next.handle(inquiry);
    }

    protected abstract boolean canHandle(Inquiry inquiry);

    protected abstract void process(Inquiry inquiry);
}
