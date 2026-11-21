package task1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class OrderQueue {
    private final Deque<MedicalOrder> pending = new ArrayDeque<>();

    public void add(MedicalOrder order) {
        pending.addLast(order);
    }

    public MedicalOrder removeLast() {
        return pending.isEmpty() ? null : pending.removeLast();
    }

    public MedicalOrder pollNext() {
        return pending.isEmpty() ? null : pending.removeFirst();
    }

    public boolean isEmpty() {
        return pending.isEmpty();
    }

    public List<MedicalOrder> snapshot() {
        return new ArrayList<>(pending);
    }
}
