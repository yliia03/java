package hospital.command;

public class CancelProcedureOrder implements MedicalOrder {
    private final PatientChart chart;
    private final String procedure;
    private boolean wasPresent;

    public CancelProcedureOrder(PatientChart chart, String procedure) {
        this.chart = chart;
        this.procedure = procedure;
    }

    @Override
    public void execute() {
        wasPresent = chart.removeProcedure(procedure);
    }

    @Override
    public void undo() {
        if (wasPresent) {
            chart.addProcedure(procedure);
        }
    }

    @Override
    public String description() {
        return "скасувати процедуру «" + procedure + "»";
    }
}
