package task1_bridge;

public class HealthDepartmentStatReport extends MedicalReport {
    private final String period;
    private final String indicators;

    public HealthDepartmentStatReport(ReportFormat format, String period, String indicators) {
        super(format);
        this.period = period;
        this.indicators = indicators;
    }

    @Override
    public String generate() {
        return format.export("Статистична форма ДОЗ (" + period + ")", indicators);
    }
}
