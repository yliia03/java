package task1_bridge;

public class PdfFormat implements ReportFormat {
    @Override
    public String getFormatName() { return "PDF-документ"; }

    @Override
    public String export(String title, String content) {
        return "%PDF-1.7 { Title: '" + title + "', Body: '" + content + "' }";
    }
}
