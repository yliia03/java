package hospital.discharge;

public class SurgeryEpicrisis extends AbstractDischargeDocument {

    public SurgeryEpicrisis(Patient patient) {
        super(patient);
    }

    @Override
    public String title() {
        return "Епікриз із протоколом операції";
    }

    @Override
    public String specificSection() {
        return "Протокол операції: " + patient.data("протокол операції");
    }
}
