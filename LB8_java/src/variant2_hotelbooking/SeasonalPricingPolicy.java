package variant2_hotelbooking;

public class SeasonalPricingPolicy implements ISeasonalPricingStrategy {
    @Override
    public double calculateMultiplier(String season) {
        if ("Peak".equalsIgnoreCase(season)) return 1.5;
        if ("High".equalsIgnoreCase(season)) return 1.25;
        if ("Low".equalsIgnoreCase(season)) return 0.9;
        return 1.0;
    }
}