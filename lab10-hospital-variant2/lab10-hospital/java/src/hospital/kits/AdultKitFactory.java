package hospital.kits;

public class AdultKitFactory implements MedicalKitFactory {
    @Override
    public BloodPressureCuff createCuff() {
        return new AdultCuff();
    }

    @Override
    public IvCatheter createCatheter() {
        return new AdultCatheter();
    }

    @Override
    public DosingSyringe createSyringe() {
        return new AdultSyringe();
    }

    @Override
    public String categoryName() {
        return "дорослі";
    }
}
