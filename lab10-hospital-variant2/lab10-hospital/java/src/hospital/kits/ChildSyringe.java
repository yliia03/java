package hospital.kits;

public class ChildSyringe implements DosingSyringe {
    @Override
    public String category() {
        return "діти";
    }

    @Override
    public String describe() {
        return "дитячий шприц-дозатор 20 мл";
    }

    @Override
    public String dosageSetting() {
        return "0,5 мл/год, крок 0,01 мл";
    }
}
