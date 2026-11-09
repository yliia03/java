package hospital.kits;

public class AdultCatheter implements IvCatheter {
    @Override
    public String category() {
        return "дорослі";
    }

    @Override
    public String describe() {
        return "внутрішньовенний катетер 18G";
    }
}
