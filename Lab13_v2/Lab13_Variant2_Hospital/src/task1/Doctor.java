package task1;

public class Doctor {
    private final String name;
    private final OrderQueue queue;
    private final EventLog log;

    public Doctor(String name, OrderQueue queue, EventLog log) {
        this.name = name;
        this.queue = queue;
        this.log = log;
    }

    public void prescribe(MedicalOrder order) {
        queue.add(order);
        log.log(name, "Додано в чергу призначення: " + order.description());
    }

    public void revokeLast() {
        MedicalOrder removed = queue.removeLast();
        if (removed != null) {
            log.log(name, "Відкликано останнє невиконане призначення: " + removed.description());
        } else {
            log.log(name, "Немає невиконаних призначень для відкликання.");
        }
    }

    public void printQueue() {
        log.log(name, "Поточна черга очікування (" + queue.snapshot().size() + " шт.)");
    }
}
