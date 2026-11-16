package task1_bridge;

public class ElectronicGovFormat implements ReportFormat {
    @Override
    public String getFormatName() { return "Електронний документ ЕСОЗ (XML + КЕП)"; }

    @Override
    public String export(String title, String content) {
        return "<eHealthDoc title=\"" + title + "\">" + content + "</eHealthDoc>";
    }
}
