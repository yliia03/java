package variant2_discountcalculation;

import java.math.BigDecimal;

public class DiscountCalculatorTest {

    public static void main(String[] args) {
        testRegularCustomerDiscount();
        testVipCustomerDiscount();
        testStudentDiscountOCP();
        System.out.println("✅ Усі тести успішно пройдені!");
    }

    public static void testRegularCustomerDiscount() {
        DiscountStrategy strategy = new RegularCustomerDiscount();
        Order order = new Order("TEST-1", new BigDecimal("150.00"), 2, false);
        BigDecimal discount = strategy.calculateDiscount(order);
        
        assert discount.compareTo(new BigDecimal("7.50")) == 0 : "Помилка в розрахунку Regular";
    }

    public static void testVipCustomerDiscount() {
        DiscountStrategy strategy = new VipCustomerDiscount();
        Order order = new Order("TEST-2", new BigDecimal("200.00"), 6, false);
        BigDecimal discount = strategy.calculateDiscount(order);
        
        assert discount.compareTo(new BigDecimal("40.00")) == 0 : "Помилка в розрахунку VIP";
    }

    public static void testStudentDiscountOCP() {
        DiscountStrategy strategy = new StudentDiscount();
        Order order = new Order("TEST-3", new BigDecimal("100.00"), 1, false);
        BigDecimal discount = strategy.calculateDiscount(order);
        
        assert discount.compareTo(new BigDecimal("12.00")) == 0 : "Помилка в розрахунку Student";
    }
}
