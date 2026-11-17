
package online_shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class QuantityRequirementsTest {

    private int testQuantity(String input) {
        try (Scanner scanner = new Scanner(input)) {
            return App.readValidQuantity(scanner);
        }
    }

    // REQ-01: Accept positive whole numbers
    @Test
    void acceptsPositiveQuantity() {
        assertEquals(5, testQuantity("5\n"));
    }

    // REQ-02: Accept the minimum valid quantity
    @Test
    void acceptsMinimumQuantity() {
        assertEquals(1, testQuantity("1\n"));
    }

    // REQ-03: Reject zero, allow retry
    @Test
    void rejectsZero() {
        assertEquals(3, testQuantity("0\n3\n"));
    }

    //REQ-04: Reject negative values
    @Test
    void rejectsNegativeQuantity() {
        assertEquals(4, testQuantity("-2\n4\n"));
    }

    // REQ-05: Reject text input
    @Test
    void rejectsNonNumericInput() {
        assertEquals(2, testQuantity("abc\n2\n"));
    }

    // REQ-06: Reject decimals
    @Test
    void rejectsDecimalInput() {
        assertEquals(2, testQuantity("1.5\n2\n"));
    }
}
