package hospital.kits;

public class AdultCuff implements BloodPressureCuff {
    @Override
    public String category() {
        return "дорослі";
    }

    @Override
    public String describe() {
        return "доросла манжета тонометра (обхват 25–35 см)";
    }
}
