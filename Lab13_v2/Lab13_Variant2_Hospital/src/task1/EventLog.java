package task1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventLog {
    private LocalTime time = LocalTime.of(8, 30);
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm");

    public void log(String source, String event) {
        System.out.printf("[%s] [%s] %s%n", nowText(), source, event);
    }

    public LocalTime now() {
        return time;
    }

    public String nowText() {
        return time.format(FMT);
    }

    public void advanceMinutes(int minutes) {
        this.time = this.time.plusMinutes(minutes);
    }
}
