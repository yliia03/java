package hospital.discharge;

public class GeneralEpicrisis extends AbstractDischargeDocument {

    public GeneralEpicrisis(Patient patient) {
        super(patient);
    }

    @Override
    public String title() {
        return "Загальний епікриз";
    }

    @Override
    public String specificSection() {
        return "Перебіг лікування: " + patient.data("перебіг лікування");
    }
}
