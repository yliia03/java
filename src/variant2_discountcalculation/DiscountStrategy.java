package variant2_discountcalculation;

import java.math.BigDecimal;

public interface DiscountStrategy {
    BigDecimal calculateDiscount(Order order);
    String getCategoryName();
}