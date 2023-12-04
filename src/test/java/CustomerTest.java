
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Query.Direction;

import models.Customer;
import models.Product;
import utils.Utils;

/// NOTE: the db gets reset to it's initial state each time
public class CustomerTest extends TestUtils {
    public Customer customer0;
    public Customer customer1;
    public Customer customer2;
    public CollectionReference customers;

    // Empty to constructor to prevent super constructor from running
    public CustomerTest() {
        return;
    }

    @BeforeEach
    @Override
    public void setUp() throws IOException {
        super.setUp();
        try {
            customer0 = Customer.getCustomerByEmail("adityasemail@gmail.com");
            customer1 = Customer.getCustomerByEmail("shloksemail@gmail.com");
            customer2 = Customer.getCustomerByEmail("xandersemail@gmail.com");
            if (customers == null) {
                customers = db.collection("customers");
            }
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
        assertEquals(customers, Customer.getCustomersByIds(new ArrayList<Integer>(Arrays.asList(0, 1))));
        // assertThrows(IOException.class, () ->
        // Customer.getCustomersByIds(Arrays.asList()));
        assertEquals(Arrays.asList(), Customer.getCustomersByIds(new ArrayList<Integer>(Arrays.asList())));
    }

    @Test
    public void testSortCustomers() throws IOException {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        customers.add(customer0);
        customers.add(customer1);
        customers.add(customer2);
        assertEquals(customers, Customer.sortCustomers("customerId", Direction.ASCENDING));
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

    @Test
    public void testCreateCustomer() {
        try {
            Customer customer3 = Customer.createCustomer("kabeer@gmail.com", "password");
            assertEquals(customer3, Customer.getCustomerByEmail("kabeer@gmail.com"));
            // Ensure that trying to create an already existing customer throws an error;
            // handled in amazeon
            // assertThrows(IOException.class, () ->
            // Customer.createCustomer("adityasemail@gmail.com", "password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteCustomer() throws IOException {
        customer0.deleteCustomer();
        assertThrows(IOException.class, () -> Customer.getCustomerById(0));
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
        assertEquals(customer0,
                Customer.getCustomerByEmail("adityasnewemail@gmail.com"));
        assertThrows(IOException.class, () -> customer0.setEmail("invalidEmail:)"));
    }

    @Test
    public void testSetPassword() throws IOException {
        customer0.setPassword("adityasnewpassword");
        assertEquals(1,
                Utils.retrieveData(customers.whereEqualTo("password",
                        "adityasnewpassword").limit(1).get()).size());
        assertThrows(IOException.class, () -> customer0.setEmail("invalidPassword:)"));
    }

    @Test
    public void testSetProducts() throws IOException {
        customer1.setProducts(new ArrayList<Product>(Arrays.asList(Product.getProductById(3))));
        assertEquals(1,
                Utils.retrieveData(customers.whereEqualTo("productIds",
                        Arrays.asList(3)).limit(1).get()).size());
    }

    @Test
    public void testAddProduct() throws IOException {
        customer0.addProduct(Product.getProductById(3));
        assertEquals(1,
                Utils.retrieveData(customers.whereEqualTo("productIds", Arrays.asList(0,
                        3)).limit(1).get()).size());
    }
}
