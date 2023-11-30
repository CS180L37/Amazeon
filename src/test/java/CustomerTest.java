import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.cloud.firestore.CollectionReference;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import models.Cart;
import models.Customer;
import models.Product;
import utils.Utils;

/// NOTE: assuming that the db gets reset to it's initial state every time
public class CustomerTest extends TestUtils {
    public Customer customer0;
    public Customer customer1;
    public CollectionReference customers = db.collection("customers");

    @Override
    @BeforeEach
    public void setUp() {
        // Call initial setup
        super.setUp();
        // Initialize Customer instances
        // Normally, I would use a .env file for encoding data
        // But these are test instances
        try {
            customer0 = Customer.getCustomerByEmail("adityasemail@gmail.com");
            customer1 = Customer.getCustomerByEmail("shloksemail@gmail.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Private
    // @Test
    // public void testGetCustomerDocument() {

    // }

    // Private
    // @Test
    // public void testGetNextCustomerId() {

    // }

    @Test
    public void testGetCustomerById() throws IOException {
        assertEquals(customer0, Customer.getCustomerById(0));
        assertNull(Customer.getCustomerById(100));
    }

    @Test
    public void testGetCustomersByIds() throws IOException {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        customers.add(customer0);
        customers.add(customer1);
        assertEquals(customers, Customer.getCustomersByIds(Arrays.asList(0, 1)));
        // assertThrows(IOException.class, () ->
        // Customer.getCustomersByIds(Arrays.asList()));
        assertNull(Customer.getCustomersByIds(Arrays.asList()));
    }

    @Test
    public void testCustomerExists() throws IOException {
        assert (Customer.customerExists("adityasemail@gmail.com", "password"));
        assert (!Customer.customerExists("fakecustomer@gmail.com", "password"));
    }

    @Test
    public void testGetCustomerByEmail() throws IOException {
        assertEquals(customer0, Customer.getCustomerByEmail("adityasemail@gmail.com"));
        assertNull(Customer.getCustomerByEmail("fakecustomer@gmail.com"));
    }

    // This is implicitly tested in setup
    // @Test
    // public void testCreateCustomer() {

    // }

    @Test
    public void testDeleteCustomer() throws IOException {
        customer0.deleteCustomer();
        assertNull(Customer.getCustomerById(0));
    }

    // @Test
    // public void testSetCartId() throws IOException {
    // Cart newCart = customer0.getCart();
    // newCart.setCustomerID(3);
    // customer0.setCartId(newCart);
    // assertEquals(customer0,
    // Utils.retrieveData(customers.whereEqualTo("cartId",
    // 3).limit(1).get()).get(0));
    // }

    @Test
    public void testSetCustomerId() throws IOException {
        customer0.setCustomerId(100);
        assertEquals(customer0, Customer.getCustomerById(100));
    }

    @Test
    public void testSetEmail() throws IOException {
        customer0.setEmail("adityasnewemail@gmail.com");
        assertEquals(customer0, Customer.getCustomerByEmail("adityasnewemail@gmail.com"));
        assertThrows(IOException.class, () -> customer0.setEmail("invalidEmail:)"));
    }

    @Test
    public void testSetPassword() throws IOException {
        customer0.setPassword("adityasnewpassword");
        assertEquals(1,
                Utils.retrieveData(customers.whereEqualTo("password", "adityasnewpassword").limit(1).get()).size());
        assertThrows(IOException.class, () -> customer0.setEmail("invalidPassword:)"));
    }

    @Test
    public void testSetProducts() throws IOException {
        customer1.setProducts(new ArrayList<Product>(Arrays.asList(Product.getProductById(3))));
        assertEquals(1,
                Utils.retrieveData(customers.whereEqualTo("productIds", 3).limit(1).get()).size());
    }
}