package hospital.discharge;

public class CardiologyEpicrisis extends AbstractDischargeDocument {

    public CardiologyEpicrisis(Patient patient) {
        super(patient);
    }

    @Override
    public String title() {
        return "Епікриз з результатами ЕКГ";
    }

    @Override
    public String specificSection() {
        return "Результати ЕКГ: " + patient.data("ЕКГ");
    }
}
