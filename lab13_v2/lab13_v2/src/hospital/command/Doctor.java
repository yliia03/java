package hospital.command;

import hospital.common.EventLog;

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
        log.log("Лікар " + name, "призначив: " + order.description());
    }

    public void revokeLast() {
        MedicalOrder order = queue.removeLast();
        if (order == null) {
            log.log("Лікар " + name, "відкликати нічого — черга порожня");
        } else {
            log.log("Лікар " + name, "ВІДКЛИКАВ невиконане призначення: " + order.description());
        }
    }

    public void printQueue() {
        System.out.println("   Черга очікуваних призначень:");
        int i = 1;
        for (MedicalOrder o : queue.snapshot()) {
            System.out.println("   " + i++ + ". " + o.description());
        }
        if (i == 1) System.out.println("   (порожньо)");
    }
}
