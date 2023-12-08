import static utils.Utils.clearCollections;
import static utils.Utils.initializeCollections;
import java.util.HashMap;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.google.api.core.ApiFuture;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ServiceOptions;
import com.google.cloud.firestore.BulkWriter;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import models.Cart;
import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import models.Store;
import utils.Utils;
import utils.fields;

public class TestUtils {
        public static Firestore db = initializeTestDB();
        public static final PrintStream originalOutput = System.out;
        public static final InputStream originalSysin = System.in;
        public ByteArrayOutputStream testOut;

        public static Firestore initializeTestDB() {
                // FirestoreOptions options = FirestoreOptions.getDefaultInstance().toBuilder()
                // .build();
                Firestore FS = FirestoreOptions.getDefaultInstance().toBuilder()
                                .setProjectId("amazeon-405720")
                                .setHost("localhost:8080")
                                .setCredentials(new FirestoreOptions.EmulatorCredentials())
                                .setCredentialsProvider(FixedCredentialsProvider
                                                .create(new FirestoreOptions.EmulatorCredentials()))
                                .build()
                                .getService();
                // firestore.setFirestoreSettings(settings);
                // initialize database
                initializeCollections(FS);
                return FS;
        }

        @BeforeEach
        public void setUp() throws IOException {
                // Reinitialize data
                clearCollections();
                try {
                        Product.productsCollection.add(Map.of(
                                        fields.description,
                                        "Harry Potter and the Philosopher's Stone is a fantasy novel written by " +
                                                        "British author J. K. Rowling. The first novel in the Harry Potter series and "
                                                        +
                                                        "Rowling's debut novel, it follows Harry Potter, a young wizard who discovers "
                                                        +
                                                        "his magical heritage on his eleventh birthday, when he receives a letter of "
                                                        +
                                                        "acceptance to Hogwarts School of Witchcraft and Wizardry. Harry makes close "
                                                        +
                                                        "friends and a few enemies during his first year at the school and with the "
                                                        +
                                                        "help of his friends, Ron Weasley and Hermione Granger, he faces an attempted "
                                                        +
                                                        "comeback by the dark wizard Lord Voldemort, who killed Harry's parents, but "
                                                        +
                                                        "failed to kill Harry when he was just 15 months old.",
                                        fields.name, "Harry Potter", fields.price, 15, fields.productId, 1,
                                        fields.quantity, 10, fields.sellerId, 1, fields.storeId, 0, fields.isDeleted,
                                        false)).get();

                        Product.productsCollection.add(Map.of(
                                        fields.description,
                                        "The Lightning Thief is a light-hearted fantasy about a modern 12-year-old " +
                                                        "boy who learns that his true father is Poseidon, the Greek god of the sea. "
                                                        +
                                                        "Percy sets out to become a hero by undertaking a quest across the United "
                                                        +
                                                        "States to find the entrance to the Underworld and stop a war between the "
                                                        +
                                                        "gods.",
                                        fields.name, "Percy Jackson", fields.price, 9.99, fields.productId, 0,
                                        fields.quantity, 5, fields.sellerId, 0, fields.storeId, 0, fields.isDeleted,
                                        false)).get();
                        Product.productsCollection.add(Map.of(
                                        fields.description, "A piece of wood for hitting a ball",
                                        fields.name, "Cricket Bat", fields.price, 35, fields.productId, 2,
                                        fields.quantity, 50, fields.sellerId, 2, fields.storeId, 1, fields.isDeleted,
                                        false)).get();

                        Product.productsCollection.add(Map.of(
                                        fields.description, "Nintendo's switching it up with their new game console!",
                                        fields.name, "Nintendo Switch", fields.price, 299.99, fields.productId, 3,
                                        fields.quantity, 10, fields.sellerId, 3, fields.storeId, 2, fields.isDeleted,
                                        false)).get();
                        Cart.cartsCollection.add(Map.of(
                                        "customerId", 0, fields.productIds, Arrays.asList(), fields.isDeleted, false))
                                        .get();

                        Cart.cartsCollection.add(Map.of(
                                        "customerId", 1, fields.productIds, Arrays.asList(2), fields.isDeleted, false))
                                        .get();

                        Cart.cartsCollection.add(Map.of(
                                        "customerId", 2, fields.productIds, Arrays.asList(), fields.isDeleted, false))
                                        .get();

                        Customer.customersCollection.add(Map.of(
                                        "cartId", 0, "customerId", 0, fields.email, "adityasemail@gmail.com",
                                        fields.password, fields.password, fields.productIds, Arrays.asList(0),
                                        fields.isDeleted, false)).get();

                        Customer.customersCollection.add(Map.of(
                                        "cartId", 1, "customerId", 1, fields.email, "shloksemail@gmail.com",
                                        fields.password, fields.password, fields.productIds, Arrays.asList(),
                                        fields.isDeleted, false)).get();

                        Customer.customersCollection.add(Map.of(
                                        "cartId", 2, "customerId", 2, fields.email, "xandersemail@gmail.com",
                                        fields.password, fields.password, fields.productIds, Arrays.asList(3),
                                        fields.isDeleted, false)).get();

                        Sale.salesCollection.add(Map.of(
                                        fields.cost, 9.99, "customerId", 0, fields.numPurchased, 1, fields.productId, 0,
                                        fields.saleId, 0, fields.isDeleted, false)).get();

                        Sale.salesCollection.add(Map.of(
                                        fields.cost, 299.99, "customerId", 2, fields.numPurchased, 1, fields.productId,
                                        3,
                                        fields.saleId, 1, fields.isDeleted, false)).get();

                        Store.storesCollection.add(Map.of(
                                        "customerIds", Arrays.asList(0), fields.name, "Goodreads",
                                        fields.productIds, Arrays.asList(0, 1), fields.storeId, 0, fields.isDeleted,
                                        false)).get();

                        Store.storesCollection.add(Map.of(
                                        "customerIds", Arrays.asList(), fields.name, "Dicks Sporting Goods",
                                        fields.productIds, Arrays.asList(2), fields.storeId, 1, fields.isDeleted,
                                        false)).get();

                        Store.storesCollection.add(Map.of(
                                        "customerIds", Arrays.asList(2), fields.name, "Gamestop",
                                        fields.productIds, Arrays.asList(3), fields.storeId, 2, fields.isDeleted,
                                        false)).get();

                        Seller.sellersCollection.add(Map.of(
                                        fields.email, "rickriordan@gmail.com", fields.name, "Rick Riordan",
                                        fields.password, "lightning", fields.productIds, Arrays.asList(0),
                                        fields.saleIds, Arrays.asList(0), fields.sellerId, 0, fields.isDeleted, false))
                                        .get();

                        Seller.sellersCollection.add(Map.of(
                                        fields.email, "jkrowling@gmail.com", fields.name, "JK Rowling",
                                        fields.password, "magic", fields.productIds, Arrays.asList(1),
                                        fields.saleIds, Arrays.asList(), fields.sellerId, 1, fields.isDeleted, false))
                                        .get();

                        Seller.sellersCollection.add(Map.of(
                                        "email", "ceat@gmail.com", "name", "Ceat",
                                        "password", "magic", "productIds", Arrays.asList(2),
                                        "saleIds", Arrays.asList(), "sellerId", 2, fields.isDeleted, false)).get();

                        Seller.sellersCollection.add(Map.of(
                                        fields.email, "nintendo@nintendo.com", fields.name, "Nintendo",
                                        fields.password, "wahoo", fields.productIds, Arrays.asList(3),
                                        fields.saleIds, Arrays.asList(1), fields.sellerId, 3, fields.isDeleted, false))
                                        .get();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                        throw new IOException("Setup interrupted");
                } catch (ExecutionException e) {
                        e.printStackTrace();
                        throw new IOException("Execution interrupted");
                }
                // (re)initialize stdin and stdout just in case it was used
                this.testOut = new ByteArrayOutputStream();
                System.setOut(new PrintStream(this.testOut));
        }

        @AfterAll
        public static void tearDown() {
                try {
                        clearCollections();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                System.setOut(originalOutput);
                System.setIn(originalSysin);
        }
}
