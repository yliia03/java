package task1;

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
        log.log(name, "Починаю виконання накопичених призначень із черги...");
        while (!queue.isEmpty()) {
            MedicalOrder order = queue.pollNext();
            order.execute();
            executed.push(order);
            journal.add(new ExecutionRecord(log.nowText(), name, order.description()));
            log.log(name, "Виконано: " + order.description());
        }
    }

    public void undoLastExecuted() {
        if (executed.isEmpty()) {
            log.log(name, "Немає виконаних призначень для скасування.");
            return;
        }
        MedicalOrder last = executed.pop();
        last.undo();
        journal.add(new ExecutionRecord(log.nowText(), name, "ВІДМІНА (Undo): " + last.description()));
        log.log(name, "Скасовано останнє виконане призначення: " + last.description());
    }

    public void printJournal() {
        System.out.println("   --- Журнал виконаних дій медсестри (" + name + ") ---");
        for (ExecutionRecord rec : journal) {
            System.out.println("   " + rec);
        }
    }
}
