package online_shop;

import java.util.Scanner;

public class QuantityValidationTest {

    public static void main(String[] args) {
        int passed = 0;

        // Test 1: Accept a positive quantity
        checkQuantity("3\n", 3);
        passed++;
        System.out.println("PASS: Positive quantity accepted");

        // Test 2: Reject zero, then accept valid input
        checkQuantity("0\n5\n", 5);
        passed++;
        System.out.println("PASS: Zero quantity rejected");

        // Test 3: Reject negative quantities
        checkQuantity("-2\n4\n", 4);
        passed++;
        System.out.println("PASS: Negative quantity rejected");

        // Test 4: Reject non-numeric input
        checkQuantity("abc\n2\n", 2);
        passed++;
        System.out.println("PASS: Non-numeric input rejected");

        System.out.println(
            "All " + passed + " automated tests passed!"
        );
    }

    private static void checkQuantity(
            String input, int expected) {

        try (Scanner scanner = new Scanner(input)) {
            int actual = App.readValidQuantity(scanner);

            if (actual != expected) {
                throw new AssertionError(
                    "Expected " + expected +
                    " but received " + actual
                );
            }
        }
    }
}
