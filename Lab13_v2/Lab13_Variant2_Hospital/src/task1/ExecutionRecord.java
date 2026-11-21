package task1;

public class ExecutionRecord {
    public final String time;
    public final String executor;
    public final String description;

    public ExecutionRecord(String time, String executor, String description) {
        this.time = time;
        this.executor = executor;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("[%s] Виконавець: %s | Дія: %s", time, executor, description);
    }
}
