package task1;

public class AddMedicationOrder implements MedicalOrder {
    private final PatientChart chart;
    private final String drug;
    private final int doseMg;
    private Integer previousDose;

    public AddMedicationOrder(PatientChart chart, String drug, int doseMg) {
        this.chart = chart;
        this.drug = drug;
        this.doseMg = doseMg;
    }

    @Override
    public void execute() {
        previousDose = chart.getDose(drug);
        chart.addMedication(drug, doseMg);
    }

    @Override
    public void undo() {
        if (previousDose == null) {
            chart.removeMedication(drug);
        } else {
            chart.setDose(drug, previousDose);
        }
    }

    @Override
    public String description() {
        return String.format("Призначити препарат '%s' (%d мг)", drug, doseMg);
    }
}
