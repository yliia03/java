package hospital.command;

public class ChangeDoseOrder implements MedicalOrder {
    private final PatientChart chart;
    private final String drug;
    private final int newDoseMg;
    private Integer oldDoseMg;

    public ChangeDoseOrder(PatientChart chart, String drug, int newDoseMg) {
        this.chart = chart;
        this.drug = drug;
        this.newDoseMg = newDoseMg;
    }

    @Override
    public void execute() {
        oldDoseMg = chart.getDose(drug);
        chart.setDose(drug, newDoseMg);
    }

    @Override
    public void undo() {
        if (oldDoseMg == null) {
            chart.removeMedication(drug);
        } else {
            chart.setDose(drug, oldDoseMg);
        }
    }

    @Override
    public String description() {
        return "змінити дозу " + drug + " на " + newDoseMg + " мг";
    }
}
