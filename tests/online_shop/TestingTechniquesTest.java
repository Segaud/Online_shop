
package online_shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestingTechniquesTest {

    // Test data covering boundaries and equivalence partitions
    static Stream<Arguments> quantityTestData() {
        return Stream.of(
            Arguments.of("TC-01 Below boundary", "0\n1\n", 1),
            Arguments.of("TC-02 At boundary", "1\n", 1),
            Arguments.of("TC-03 Above boundary", "2\n", 2),
            Arguments.of("TC-04 Valid integer", "100\n", 100),
            Arguments.of("TC-05 Negative integer", "-1\n2\n", 2),
            Arguments.of("TC-06 Letters", "abc\n3\n", 3),
            Arguments.of("TC-07 Decimal", "1.5\n4\n", 4),
            Arguments.of("TC-08 Multiple invalid", "-1\n0\nabc\n5\n", 5),
            Arguments.of("TC-09 Maximum integer", "2147483647\n", 2147483647),
            Arguments.of("TC-10 Integer overflow", "2147483648\n2\n", 2),
            Arguments.of("TC-11 Special characters", "@#\n3\n", 3)

        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("quantityTestData")
    void testQuantityValidation(
            String testName, String input, int expected) {

        try (Scanner scanner = new Scanner(input)) {
            int actual = App.readValidQuantity(scanner);
            assertEquals(expected, actual, testName);
        }
    }
}
