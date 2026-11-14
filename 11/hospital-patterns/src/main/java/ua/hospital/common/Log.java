package ua.hospital.common;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public final class Log {
    private static final PrintStream OUT = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    private static int seconds = 8 * 3600;

    private Log() {
    }

    public static void log(String source, String event) {
        OUT.printf("[%02d:%02d:%02d] [%s] %s%n",
                seconds / 3600, (seconds / 60) % 60, seconds % 60, source, event);
        seconds += 5;
    }

    public static void advance(int secondsToAdd) {
        seconds += secondsToAdd;
    }

    public static void section(String title) {
        OUT.println();
        OUT.println("=== " + title + " ===");
    }
}
