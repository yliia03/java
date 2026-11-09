package hospital.kits;

public class NewbornSyringe implements DosingSyringe {
    @Override
    public String category() {
        return "новонароджені";
    }

    @Override
    public String describe() {
        return "неонатальний шприц-дозатор 10 мл";
    }

    @Override
    public String dosageSetting() {
        return "0,05 мл/год, крок 0,001 мл";
    }
}
