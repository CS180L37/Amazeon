import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import models.*;
import models.Store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.CollectionReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.fields;

public class StoreTest extends TestUtils {
    public Store store0;
    public Store store1;
    public Store store2;
    public CollectionReference stores;
    @BeforeEach
    @Override
    public void setUp() throws IOException {
        super.setUp();
        try {
            // Customer customer0 = Customer.getCustomerByEmail("adityasemail@gmail.com");
            // Customer customer1 = Customer.getCustomerByEmail("shloksemail@gmail.com");
            store0 = Store.getStoreById(0);
            store1 = Store.getStoreById(1);
            store2 = Store.getStoreById(2);
            if (stores == null) {
                stores = db.collection("Stores");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testSetName() throws ExecutionException, InterruptedException {
        store1.setName("aditya's new store");
        String fin = store1.getDocumentReference().get().get().get(fields.name).toString();
        assertEquals("aditya's new store", fin);
    }

    @Test
    public void testSetStoreId() throws ExecutionException, InterruptedException {
        store1.setStoreId(1001);
        String actual = store1.getDocumentReference().get().get().get(fields.storeId).toString();
        assertEquals(String.valueOf(1001), actual);
    }

    @Test
    public void testSetProducts() throws IOException, ExecutionException, InterruptedException {
        ArrayList<Product> newProds = new ArrayList<>(Arrays.asList(Product.getProductById(1)));
        store2.setStoreProducts(newProds);
        String exp = getProductIds(newProds).toString();
        String actual = store2.getDocumentReference().get().get().get(fields.productIds).toString();
        assertEquals(exp, actual);
    }
    public ArrayList<Integer> getProductIds(ArrayList<Product> products) {
        ArrayList<Integer> prodIds = new ArrayList<>();
        for (Product product : products) {
            prodIds.add(product.getProductId());
        }
        return prodIds;
    }

    @Test
    public void testCreateStore() throws IOException, ExecutionException, InterruptedException {
        Store store999 = Store.createStore(999, "testStore");
        assertEquals("999", Store.storesCollection.get().get().getDocuments().get(Store.storesCollection.get().get().getDocuments().size()-1).get(fields.storeId));
    }

    @Test
    public void testSortStores() throws IOException {
        ArrayList<Store> stores = new ArrayList<Store>();
        stores.add(store0);
        stores.add(store1);
        stores.add(store2);
        assertEquals(stores, Store.sortStores(fields.storeId, Query.Direction.ASCENDING));
    }
    
    @Test
    public void testSetCustomers() throws IOException, ExecutionException, InterruptedException {
        Customer customer0 = Customer.getCustomerById(0);
        Customer customer1 = Customer.getCustomerById(1);
        Customer customer2 = Customer.getCustomerById(2);
        ArrayList<Customer> customers = new ArrayList<Customer>();
        customers.add(customer0);
        customers.add(customer1);
        customers.add(customer2);
        store0.setStoreCustomers(customers);
//        assertEquals(store0.getDocumentReference().get().get().get(fields.customerId).toString());
    }


}