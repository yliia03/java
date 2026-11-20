package hospital.common;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventLog {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private LocalTime time;

    public EventLog(LocalTime start) {
        this.time = start;
    }

    public LocalTime now() {
        return time;
    }

    public String nowText() {
        return time.format(FMT);
    }

    /** Переводить умовний годинник уперед (наприклад, «медсестра прийшла пізніше»). */
    public void advanceMinutes(long minutes) {
        time = time.plusMinutes(minutes);
    }

    public void log(String source, String event) {
        System.out.printf("[%s] [%s] %s%n", nowText(), source, event);
        time = time.plusSeconds(5);
    }

    public void section(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }
}
