package variant2_discountcalculation;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<DiscountStrategy> strategies = List.of(
            new RegularCustomerDiscount(),
            new VipCustomerDiscount(),
            new WholesaleCustomerDiscount(),
            new EmployeeDiscount(),
            new StudentDiscount() 
        );

        DiscountCalculatorService service = new DiscountCalculatorService(strategies);

        Order regularOrder = new Order("ORD-001", new BigDecimal("150.00"), 2, false);
        Order vipOrder = new Order("ORD-002", new BigDecimal("200.00"), 6, false);

        service.calculateDiscount(regularOrder, "REGULAR");
        service.calculateDiscount(vipOrder, "VIP");
        service.calculateDiscount(vipOrder, "STUDENT");
    }
}