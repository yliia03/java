package task2;

public abstract class InquiryHandler {
    private InquiryHandler next;
    protected final String name;
    protected final EventLog log;

    public InquiryHandler(String name, EventLog log) {
        this.name = name;
        this.log = log;
    }

    public void setNext(InquiryHandler next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public final boolean handle(Inquiry inquiry) {
        if (canHandle(inquiry)) {
            process(inquiry);
            return true;
        }
        if (next != null) {
            log.log(name, "Не мій рівень компетенції -> передаю далі до: " + next.getName());
            return next.handle(inquiry);
        }
        log.log(name, "Кінець ланцюга! Звернення " + inquiry + " залишилося необробленим.");
        return false;
    }

    protected abstract boolean canHandle(Inquiry inquiry);
    protected abstract void process(Inquiry inquiry);
}
