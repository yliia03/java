package variant2_discountcalculation;

import java.math.BigDecimal;
import java.util.Objects;

public final class Order {
    private final String orderId;
    private final BigDecimal totalAmount;
    private final int itemCount;
    private final boolean isFirstOrder;

    public Order(String orderId, BigDecimal totalAmount, int itemCount, boolean isFirstOrder) {
        this.orderId = Objects.requireNonNull(orderId, "ID замовлення не може бути null");
        this.totalAmount = Objects.requireNonNull(totalAmount, "Сума не може бути null");
        if (itemCount < 0) {
            throw new IllegalArgumentException("Кількість товарів не може бути від'ємною");
        }
        this.itemCount = itemCount;
        this.isFirstOrder = isFirstOrder;
    }

    public String getOrderId() { return orderId; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public int getItemCount() { return itemCount; }
    public boolean isFirstOrder() { return isFirstOrder; }
}
