package hospital.kits;

public class ChildCatheter implements IvCatheter {
    @Override
    public String category() {
        return "діти";
    }

    @Override
    public String describe() {
        return "дитячий внутрішньовенний катетер 22G";
    }
}
