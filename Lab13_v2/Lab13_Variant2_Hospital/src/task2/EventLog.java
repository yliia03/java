package task2;

public class EventLog {
    public void log(String source, String event) {
        System.out.printf("[%s] %s%n", source, event);
    }
}
