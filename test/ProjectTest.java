package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProjectTest {
    private final PrintStream originalOutput = System.out;
    private final InputStream originalSysin = System.in;
    private ByteArrayOutputStream testOut;

    @After
    public void restoreSystem() {
        System.setOut(originalOutput);
        System.setIn(originalSysin);
    }

    // Redirect output to a ByteArrayOutputStream for testing
    @Before
    public void outputStart() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @Test()
    public void testOne() {
        // Set the test input
        System.setIn(new ByteArrayInputStream(
                "Your input here\n"
                        .getBytes()));

        // Run whatever code you need to

        // Check the test output against your expected output
        String expectedOutput = "Your output here\n";
        String actualOutput = testOut.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testTwo() {
        // Set the test input
        System.setIn(new ByteArrayInputStream(
                "Your input here\n"
                        .getBytes()));

        // Run whatever code you need to

        // Check the test output against your expected output
        String expectedOutput = "Your output here\n";
        String actualOutput = testOut.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testThree() {
        // Set the test input
        System.setIn(new ByteArrayInputStream(
                "Your input here\n"
                        .getBytes()));

        // Run whatever code you need to

        // Check the test output against your expected output
        String expectedOutput = "Your output here\n";
        String actualOutput = testOut.toString();
        assertEquals(expectedOutput, actualOutput);
    }
}