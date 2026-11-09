package hospital.kits;

public class NewbornKitFactory implements MedicalKitFactory {
    @Override
    public BloodPressureCuff createCuff() {
        return new NewbornCuff();
    }

    @Override
    public IvCatheter createCatheter() {
        return new NewbornCatheter();
    }

    @Override
    public DosingSyringe createSyringe() {
        return new NewbornSyringe();
    }

    @Override
    public String categoryName() {
        return "новонароджені";
    }
}
