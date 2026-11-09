package hospital.kits;

public class NewbornCuff implements BloodPressureCuff {
    @Override
    public String category() {
        return "новонароджені";
    }

    @Override
    public String describe() {
        return "неонатальна манжета тонометра (обхват 6–11 см)";
    }
}
