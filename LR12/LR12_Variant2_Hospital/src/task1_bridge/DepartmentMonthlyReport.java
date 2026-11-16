package task1_bridge;

public class DepartmentMonthlyReport extends MedicalReport {
    private final String department;
    private final int treatedCount;

    public DepartmentMonthlyReport(ReportFormat format, String department, int treatedCount) {
        super(format);
        this.department = department;
        this.treatedCount = treatedCount;
    }

    @Override
    public String generate() {
        return format.export("Звіт відділення за місяць: " + department, "Проліковано пацієнтів: " + treatedCount);
    }
}

