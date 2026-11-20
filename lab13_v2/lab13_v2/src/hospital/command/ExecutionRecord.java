package hospital.command;

public record ExecutionRecord(String time, String executor, String description) {
    @Override
    public String toString() {
        return "[" + time + "] " + executor + " виконала: " + description;
    }
}
