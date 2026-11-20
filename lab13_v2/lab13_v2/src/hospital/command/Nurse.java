package hospital.command;

import hospital.common.EventLog;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Nurse {
    private final String name;
    private final OrderQueue queue;
    private final EventLog log;
    private final List<ExecutionRecord> journal = new ArrayList<>();
    private final Deque<MedicalOrder> executed = new ArrayDeque<>();

    public Nurse(String name, OrderQueue queue, EventLog log) {
        this.name = name;
        this.queue = queue;
        this.log = log;
    }

    public void executeAll() {
        MedicalOrder order;
        while ((order = queue.pollNext()) != null) {
            order.execute();
            executed.push(order);
            ExecutionRecord rec = new ExecutionRecord(log.nowText(), "Медсестра " + name, order.description());
            journal.add(rec);
            log.log("Медсестра " + name, "виконала: " + order.description());
        }
    }

    public void undoLastExecuted() {
        MedicalOrder order = executed.poll();
        if (order == null) {
            log.log("Медсестра " + name, "скасовувати нічого");
            return;
        }
        order.undo();
        log.log("Медсестра " + name, "скасувала виконане: " + order.description());
    }

    public void printJournal() {
        System.out.println("   Журнал виконаних дій:");
        if (journal.isEmpty()) System.out.println("   (порожньо)");
        for (ExecutionRecord r : journal) {
            System.out.println("   " + r);
        }
    }
}
