package variant2_discountcalculation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EmployeeDiscount implements DiscountStrategy {
    @Override
    public BigDecimal calculateDiscount(Order order) {
        return order.getTotalAmount()
                .multiply(new BigDecimal("0.30"))
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getCategoryName() { return "EMPLOYEE"; }
}