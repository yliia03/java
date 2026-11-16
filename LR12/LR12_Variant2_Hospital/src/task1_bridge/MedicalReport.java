package task1_bridge;

public abstract class MedicalReport {
    protected ReportFormat format;

    protected MedicalReport(ReportFormat format) {
        this.format = format;
    }

    public void setFormat(ReportFormat format) {
        this.format = format;
    }

    public abstract String generate();
}
