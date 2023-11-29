import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

public class TestUtils {
    public Firestore db;
    public final PrintStream originalOutput;
    public final InputStream originalSysin;
    public ByteArrayOutputStream testOut;

    public TestUtils() {
        this.originalOutput = System.out;
        this.originalSysin = System.in;
    }

    @Before
    public void setUp() {
        // Set up Firestore emulator so data changes don't get written to backend
        FirestoreOptions options = FirestoreOptions.getDefaultInstance().toBuilder()
                .setProjectId("amazeon-405720")
                .setHost("localhost:8080")
                .build();
        // (re)initialize database
        db = options.getService();
        // (re)initialize stdin and stdout if necessary
        this.testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.testOut));
    }

    @After
    public void tearDown() {
        System.setOut(originalOutput);
        System.setIn(originalSysin);
        try {
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
