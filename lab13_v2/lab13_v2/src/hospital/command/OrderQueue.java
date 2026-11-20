package hospital.command;

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
        return pending.pollLast();
    }

    public MedicalOrder pollNext() {
        return pending.pollFirst();
    }

    public boolean isEmpty() {
        return pending.isEmpty();
    }

    public List<MedicalOrder> snapshot() {
        return new ArrayList<>(pending);
    }
}
