package task1_bridge;

public class CsvFormat implements ReportFormat {
    @Override
    public String getFormatName() { return "CSV-таблиця"; }

    @Override
    public String export(String title, String content) {
        return "\"" + title + "\";\"" + content + "\"";
    }
}
