package hospital.kits;

public interface MedicalKitFactory {

    BloodPressureCuff createCuff();

    IvCatheter createCatheter();

    DosingSyringe createSyringe();

    String categoryName();
}
