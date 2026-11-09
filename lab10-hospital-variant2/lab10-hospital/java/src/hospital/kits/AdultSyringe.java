package hospital.kits;

public class AdultSyringe implements DosingSyringe {
    @Override
    public String category() {
        return "дорослі";
    }

    @Override
    public String describe() {
        return "шприц-дозатор 50 мл";
    }

    @Override
    public String dosageSetting() {
        return "5,0 мл/год, крок 0,1 мл";
    }
}
