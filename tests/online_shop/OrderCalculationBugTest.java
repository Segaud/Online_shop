package online_shop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class OrderCalculationBugTest {

    @Test
    void calculatesValidOrderTotal() {
        double result =
            OrdersDAO.calculateItemTotal(4.50, 2);

        assertEquals(9.00, result, 0.001);
    }

    @Test
    void rejectsNegativeQuantity() {
        assertThrows(
            IllegalArgumentException.class,
            () -> OrdersDAO.calculateItemTotal(4.50, -2)
        );
    }

    @Test
    void rejectsZeroQuantity() {
        assertThrows(
            IllegalArgumentException.class,
            () -> OrdersDAO.calculateItemTotal(4.50, 0)
        );
    }
}
