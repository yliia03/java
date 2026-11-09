package hospital.kits;

public class ChildCuff implements BloodPressureCuff {
    @Override
    public String category() {
        return "діти";
    }

    @Override
    public String describe() {
        return "дитяча манжета тонометра (обхват 15–21 см)";
    }
}
