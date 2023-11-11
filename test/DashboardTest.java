
// import static org.junit.Assert.assertEquals;

// import java.io.ByteArrayInputStream;
// import java.io.ByteArrayOutputStream;
// import java.io.InputStream;
// import java.io.PrintStream;

// import org.junit.After;
// import org.junit.Before;
// import org.junit.Ignore;
// import org.junit.Test;

// @Ignore
// public class DashboardTest {
// private final PrintStream originalOutput = System.out;
// private final InputStream originalSysin = System.in;
// private ByteArrayOutputStream testOut;

// @After
// public void restoreSystem() {
// System.setOut(originalOutput);
// System.setIn(originalSysin);
// }

// // Redirect output to a ByteArrayOutputStream for testing
// @Before
// public void outputStart() {
// testOut = new ByteArrayOutputStream();
// System.setOut(new PrintStream(testOut));
// }
// }