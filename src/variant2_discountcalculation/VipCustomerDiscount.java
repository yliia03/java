package variant2_discountcalculation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VipCustomerDiscount implements DiscountStrategy {
    @Override
    public BigDecimal calculateDiscount(Order order) {
        BigDecimal amount = order.getTotalAmount();
        BigDecimal discount = amount.multiply(new BigDecimal("0.15"));

        if (order.getItemCount() > 5) {
            discount = discount.add(amount.multiply(new BigDecimal("0.05")));
        }

        return discount.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getCategoryName() { return "VIP"; }
}