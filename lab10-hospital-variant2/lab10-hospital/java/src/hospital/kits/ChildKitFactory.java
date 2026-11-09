package hospital.kits;

public class ChildKitFactory implements MedicalKitFactory {
    @Override
    public BloodPressureCuff createCuff() {
        return new ChildCuff();
    }

    @Override
    public IvCatheter createCatheter() {
        return new ChildCatheter();
    }

    @Override
    public DosingSyringe createSyringe() {
        return new ChildSyringe();
    }

    @Override
    public String categoryName() {
        return "діти";
    }
}
