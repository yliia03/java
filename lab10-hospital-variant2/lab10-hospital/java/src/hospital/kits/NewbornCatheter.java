package hospital.kits;

public class NewbornCatheter implements IvCatheter {
    @Override
    public String category() {
        return "новонароджені";
    }

    @Override
    public String describe() {
        return "неонатальний внутрішньовенний катетер 24G";
    }
}
