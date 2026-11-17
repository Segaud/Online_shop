
package online_shop;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;

import org.junit.jupiter.api.Test;

public class TestingLevelsTest {

    // INTEGRATION TEST
    // Checks quantity validation and order calculation together
    @Test
    void integrationTestOrderCalculation() {
        try (Scanner scanner = new Scanner("0\n3\n")) {
            int quantity = App.readValidQuantity(scanner);

            double total =
                OrdersDAO.calculateItemTotal(4.50, quantity);

            assertEquals(3, quantity);
            assertEquals(13.50, total, 0.001);
        }
    }

    // SYSTEM SMOKE TEST
    // Starts the application, tests invalid menu input and exits
    @Test
    void systemTestMenuAndExit() throws Exception {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream input =
            new ByteArrayInputStream(
                "abc\n0\n".getBytes(StandardCharsets.UTF_8)
            );

        ByteArrayOutputStream output =
            new ByteArrayOutputStream();

        try (PrintStream capture =
                new PrintStream(output, true, StandardCharsets.UTF_8)) {

            System.setIn(input);
            System.setOut(capture);

            App.main(new String[0]);

        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        String result = output.toString(StandardCharsets.UTF_8);

        assertTrue(result.contains("Invalid input"));
        assertTrue(result.contains("Thank you and come again!"));
    }

    // COMPONENT LOAD TEST
    // Runs 80,000 calculations across 8 concurrent workers
    @Test
    void loadTestOrderCalculation() throws Exception {
        ExecutorService workers = Executors.newFixedThreadPool(8);

        List<Callable<Boolean>> tasks = new ArrayList<>();

        for (int worker = 0; worker < 8; worker++) {
            tasks.add(() -> {
                for (int i = 0; i < 10000; i++) {
                    if (OrdersDAO.calculateItemTotal(4.50, 2) != 9.00) {
                        return false;
                    }
                }
                return true;
            });
        }

        long start = System.nanoTime();

        try {
            List<Future<Boolean>> results =
                workers.invokeAll(tasks, 10, TimeUnit.SECONDS);

            for (Future<Boolean> result : results) {
                assertFalse(result.isCancelled(), "Load test timed out");
                assertTrue(result.get(), "Incorrect calculation");
            }

            long elapsedMs =
                TimeUnit.NANOSECONDS.toMillis(
                    System.nanoTime() - start
                );

            System.out.println(
                "80,000 calculations completed in " +
                elapsedMs + " ms"
            );

            assertTrue(elapsedMs <= 10000,
                "Load test exceeded 10 seconds");

        } finally {
            workers.shutdownNow();
        }
    }
}
