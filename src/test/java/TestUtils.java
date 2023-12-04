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

public class TestUtils {
        public static Firestore db;
        public static final PrintStream originalOutput = System.out;
        public static final InputStream originalSysin = System.in;
        public ByteArrayOutputStream testOut;

        public TestUtils() {
                FirestoreOptions options = FirestoreOptions.getDefaultInstance().toBuilder()
                                .build();
                // initialize database
                db = options.getService();
                initializeCollections(db);
        }

        @BeforeEach
        public void setUp() throws IOException {
                // Reinitialize data
                clearCollections(db);
                try {
                        // Write all the data
                        BulkWriter writer = db.bulkWriter();
                        writer.create(Product.productsCollection.document(), Map.of(
                                        "description",
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
                                        "name", "Harry Potter", "price", 15, "productId", 1,
                                        "quantity", 10, "sellerId", 1, "storeId", 0));

                        writer.create(Product.productsCollection.document(), Map.of(
                                        "description",
                                        "The Lightning Thief is a light-hearted fantasy about a modern 12-year-old " +
                                                        "boy who learns that his true father is Poseidon, the Greek god of the sea. "
                                                        +
                                                        "Percy sets out to become a hero by undertaking a quest across the United "
                                                        +
                                                        "States to find the entrance to the Underworld and stop a war between the "
                                                        +
                                                        "gods.",
                                        "name", "Percy Jackson", "price", 9.99, "productId", 0,
                                        "quantity", 5, "sellerId", 0, "storeId", 0));
                        writer.create(Product.productsCollection.document(), Map.of(
                                        "description", "A piece of wood for hitting a ball",
                                        "name", "Cricket Bat", "price", 35, "productId", 2,
                                        "quantity", 50, "sellerId", 2, "storeId", 1));

                        writer.create(Product.productsCollection.document(), Map.of(
                                        "description", "Nintendo's switching it up with their new game console!",
                                        "name", "Nintendo Switch", "price", 299.99, "productId", 3,
                                        "quantity", 10, "sellerId", 3, "storeId", 2));

                        writer.create(Cart.cartsCollection.document(), Map.of(
                                        "customerId", 0, "productIds", Arrays.asList()));

                        writer.create(Cart.cartsCollection.document(), Map.of(
                                        "customerId", 1, "productIds", Arrays.asList(2)));

                        writer.create(Cart.cartsCollection.document(), Map.of(
                                        "customerId", 2, "productIds", Arrays.asList()));

                        writer.create(Customer.customersCollection.document(), Map.of(
                                        "cartId", 0, "customerId", 0, "email", "adityasemail@gmail.com",
                                        "password", "password", "productIds", Arrays.asList(0)));

                        writer.create(Customer.customersCollection.document(), Map.of(
                                        "cartId", 1, "customerId", 1, "email", "shloksemail@gmail.com",
                                        "password", "password", "productIds", Arrays.asList()));

                        writer.create(Customer.customersCollection.document(), Map.of(
                                        "cartId", 2, "customerId", 2, "email", "xandersemail@gmail.com",
                                        "password", "password", "productIds", Arrays.asList(3)));

                        writer.create(Sale.salesCollection.document(), Map.of(
                                        "cost", 9.99, "customerId", 0, "numPurchased", 1, "productId", 0,
                                        "saleId", 0));

                        writer.create(Sale.salesCollection.document(), Map.of(
                                        "cost", 299.99, "customerId", 2, "numPurchased", 1, "productId", 3,
                                        "saleId", 1));

                        writer.create(Store.storesCollection.document(), Map.of(
                                        "customerIds", Arrays.asList(0), "name", "Goodreads",
                                        "productIds", Arrays.asList(0, 1), "storeId", 0));

                        writer.create(Store.storesCollection.document(), Map.of(
                                        "customerIds", Arrays.asList(), "name", "Dicks Sporting Goods",
                                        "productIds", Arrays.asList(2), "storeId", 1));

                        writer.create(Store.storesCollection.document(), Map.of(
                                        "customerIds", Arrays.asList(2), "name", "Gamestop",
                                        "productIds", Arrays.asList(3), "storeId", 2));

                        writer.create(Seller.sellersCollection.document(), Map.of(
                                        "email", "rickriordan@gmail.com", "name", "Rick Riordan",
                                        "password", "lightning", "productIds", Arrays.asList(0),
                                        "saleIds", Arrays.asList(0), "sellerId", 0));

                        writer.create(Seller.sellersCollection.document(), Map.of(
                                        "email", "jkrowling@gmail.com", "name", "JK Rowling",
                                        "password", "magic", "productIds", Arrays.asList(1),
                                        "saleIds", Arrays.asList(), "sellerId", 1));

                        writer.create(Seller.sellersCollection.document(), Map.of(
                                        "email", "ceat@gmail.com", "name", "Ceat",
                                        "password", "magic", "cricket", Arrays.asList(2),
                                        "saleIds", Arrays.asList(), "sellerId", 2));

                        writer.create(Seller.sellersCollection.document(), Map.of(
                                        "email", "nintendo@nintendo.com", "name", "Nintendo",
                                        "password", "wahoo", "productIds", Arrays.asList(3),
                                        "saleIds", Arrays.asList(1), "sellerId", 3));
                        // Writes all
                        writer.close();
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
                        db.shutdown();
                        db.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                System.setOut(originalOutput);
                System.setIn(originalSysin);
        }
}
