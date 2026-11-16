import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventLogger {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void log(String source, String event) {
        String time = LocalTime.now().format(FMT);
        System.out.printf("[%s] [%s] %s%n", time, source, event);
    }
}
