package hospital.discharge;

public class PediatricEpicrisis extends AbstractDischargeDocument {

    public PediatricEpicrisis(Patient patient) {
        super(patient);
    }

    @Override
    public String title() {
        return "Педіатричний епікриз зі щепленнями";
    }

    @Override
    public String specificSection() {
        return "Щеплення та розвиток: " + patient.data("щеплення");
    }
}
