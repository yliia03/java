package hospital.kits;

public final class MedicalKit {

    private final BloodPressureCuff cuff;
    private final IvCatheter catheter;
    private final DosingSyringe syringe;

    public MedicalKit(BloodPressureCuff cuff, IvCatheter catheter, DosingSyringe syringe) {
        this.cuff = cuff;
        this.catheter = catheter;
        this.syringe = syringe;
    }

    public BloodPressureCuff getCuff() {
        return cuff;
    }

    public IvCatheter getCatheter() {
        return catheter;
    }

    public DosingSyringe getSyringe() {
        return syringe;
    }

    @Override
    public String toString() {
        return "Набір виробів\n" + "  1) Манжета: " + cuff.describe() + "\n"+ "  2) Катетер: " + catheter.describe() + "\n" + "  3) Шприц-дозатор: " + syringe.describe()+ " (дозування: " + syringe.dosageSetting() + ")";
    }
}
