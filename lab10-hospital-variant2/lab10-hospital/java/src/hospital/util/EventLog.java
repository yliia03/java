package hospital.util;

public final class EventLog {

    private static int clockSeconds = 8 * 3600;

    private EventLog() {
    }

    public static void log(String source, String event) {
        clockSeconds += 37;
        int h = (clockSeconds / 3600) % 24;
        int m = (clockSeconds / 60) % 60;
        int s = clockSeconds % 60;
        System.out.printf("[%02d:%02d:%02d] [%s] %s%n", h, m, s, source, event);
    }

    public static void section(String title) {
        System.out.println();
        System.out.println("================ " + title + " ================");
    }

    public static void block(String text) {
        for (String line : text.split("\n")) {
            System.out.println("      | " + line);
        }
    }
}
