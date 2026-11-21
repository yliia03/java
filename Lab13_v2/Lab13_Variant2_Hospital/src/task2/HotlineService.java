package task2;

import java.util.List;

public class HotlineService {
    private final EventLog log;
    private InquiryHandler head;
    private String modeName;

    public HotlineService(EventLog log) {
        this.log = log;
    }

    public void configure(String modeName, List<InquiryHandler> handlers) {
        this.modeName = modeName;
        if (handlers == null || handlers.isEmpty()) {
            this.head = null;
            return;
        }
        this.head = handlers.get(0);
        for (int i = 0; i < handlers.size() - 1; i++) {
            handlers.get(i).setNext(handlers.get(i + 1));
        }
        handlers.get(handlers.size() - 1).setNext(null);
        log.log("Гаряча лінія", "Налаштовано режим '" + modeName + "'. Перша ланка: " + head.getName());
    }

    public void submit(Inquiry inquiry) {
        log.log("Гаряча лінія (" + modeName + ")", "Отримано звернення " + inquiry);
        if (head == null) {
            log.log("Гаряча лінія", "Ланцюг обробників порожній!");
            return;
        }
        head.handle(inquiry);
    }
}
