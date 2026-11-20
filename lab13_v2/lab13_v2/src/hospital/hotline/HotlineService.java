package hospital.hotline;

import hospital.common.EventLog;
import java.util.List;

public class HotlineService {
    private final EventLog log;
    private InquiryHandler head;
    private String modeName = "—";

    public HotlineService(EventLog log) {
        this.log = log;
    }

    public void configure(String modeName, List<InquiryHandler> handlers) {
        this.modeName = modeName;
        for (int i = 0; i < handlers.size() - 1; i++) {
            handlers.get(i).setNext(handlers.get(i + 1));
        }
        if (!handlers.isEmpty()) {
            handlers.get(handlers.size() - 1).setNext(null);
        }
        this.head = handlers.isEmpty() ? null : handlers.get(0);
        StringBuilder chain = new StringBuilder();
        for (InquiryHandler h : handlers) {
            if (chain.length() > 0) chain.append(" → ");
            chain.append(h.getName());
        }
        log.log("Гаряча лінія", "режим «" + modeName + "», ланцюг: " + chain);
    }

    public void submit(Inquiry inquiry) {
        log.log("Гаряча лінія", "нове " + inquiry);
        boolean handled = head != null && head.handle(inquiry);
        if (!handled) {
            log.log("Гаряча лінія", "ЗВЕРНЕННЯ #" + inquiry.id() + " НЕ ОБРОБИВ ЖОДЕН РІВЕНЬ");
            log.log("Гаряча лінія", "повідомлення для " + inquiry.caller() + ": «Ваше звернення виходить за межі компетенції гарячої лінії. " + "Зверніться до реєстратури лікарні або залиште заявку на сайті.»");
        }
    }
}
