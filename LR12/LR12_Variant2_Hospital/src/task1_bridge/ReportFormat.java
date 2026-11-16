package task1_bridge;

public interface ReportFormat {
    String getFormatName();
    String export(String title, String content);
}
