package variant2_discountcalculation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RegularCustomerDiscount implements DiscountStrategy {
    @Override
    public BigDecimal calculateDiscount(Order order) {
        BigDecimal amount = order.getTotalAmount();
        BigDecimal discount = BigDecimal.ZERO;

        if (order.isFirstOrder()) {
            discount = amount.multiply(new BigDecimal("0.10"));
        } else if (amount.compareTo(new BigDecimal("100.00")) >= 0) {
            discount = amount.multiply(new BigDecimal("0.05"));
        }

        return discount.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getCategoryName() { return "REGULAR"; }
}