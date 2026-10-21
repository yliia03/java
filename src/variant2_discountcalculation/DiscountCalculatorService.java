package variant2_discountcalculation;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountCalculatorService {
    private final Map<String, DiscountStrategy> strategies = new HashMap<>();

    public DiscountCalculatorService(List<DiscountStrategy> strategyList) {
        for (DiscountStrategy strategy : strategyList) {
            this.strategies.put(strategy.getCategoryName().toUpperCase(), strategy);
        }
    }

    public BigDecimal calculateDiscount(Order order, String category) {
        DiscountStrategy strategy = strategies.get(category.toUpperCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Категорія " + category + " не підтримується");
        }
        
        BigDecimal discount = strategy.calculateDiscount(order);
        System.out.println("[DISCOUNT] Замовлення " + order.getOrderId() + ": Категорія=" + category + ", Знижка=$" + discount);
        return discount;
    }
}