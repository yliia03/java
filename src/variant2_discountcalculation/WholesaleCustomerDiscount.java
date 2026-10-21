package variant2_discountcalculation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WholesaleCustomerDiscount implements DiscountStrategy {
    @Override
    public BigDecimal calculateDiscount(Order order) {
        BigDecimal amount = order.getTotalAmount();
        BigDecimal discount;

        if (amount.compareTo(new BigDecimal("1000.00")) >= 0) {
            discount = amount.multiply(new BigDecimal("0.20"));
        } else {
            discount = amount.multiply(new BigDecimal("0.10"));
        }

        return discount.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getCategoryName() { return "WHOLESALE"; }
}