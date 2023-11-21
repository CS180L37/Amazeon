
/// The mock data used for all the tests
/// NOTE: I'm not testing any getters and setters unless they have additional functionality, 
/// e.g. getById, setById (this includes the getters w/ generic typing) 
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;

public class TestUtils {
    // Dashboard and market are parent classes, so general usecases methods are
    // provided for child classes
    // to test the inherited functionality
    // Not used for actual tests, but mutable copies are made
    public Amazeon amazeon;
    public final PrintStream originalOutput;
    public final InputStream originalSysin;
    public ByteArrayOutputStream testOut;

    // Initialize what needs to be initialized
    public TestUtils() {
        this.amazeon = new Amazeon();
        this.originalOutput = System.out;
        this.originalSysin = System.in;
    }

    @Before
    public void setUp() {
        this.amazeon = new Amazeon();
        this.testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.testOut)); // Set SysOut
    }

    @After
    public void tearDown() {
        System.setOut(originalOutput);
        System.setIn(originalSysin);
    }
}
