package task1_bridge;

public class PrintFormat implements ReportFormat {
    @Override
    public String getFormatName() { return "Друк (Паперовий бланк А4)"; }

    @Override
    public String export(String title, String content) {
        return "[PRINT_JOB] Шапка лікарні | Заголовок: " + title + " | Дані: " + content;
    }
}
