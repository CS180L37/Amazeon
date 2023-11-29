
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import models.Customer;

public class CustomerTest extends TestUtils {
    public Customer customer3;
    public Customer customer4;

    @Override
    @Before
    public void setUp() {
        // Call initial setup
        super.setUp();
        // Initialize Customer instances
        try {
            customer3 = Customer.getCustomerByEmail("adityasemail@gmail.com");
            customer4 = Customer.createCustomer("nehasemail@gmail.com", "password");
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
        assertEquals(customer4, Customer.getCustomerById(3));
    }

    @Test
    public void testGetCustomersByIds() throws IOException {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        customers.add(customer3);
        customers.add(customer4);
        assertEquals(customers, Customer.getCustomersByIds(Arrays.asList(2, 3)));
    }

    @Test
    public void testCustomerExists() {

    }

    @Test
    public void testGetCustomerByEmail() {

    }

    // This is implicitly tested in setup
    // @Test
    // public void testCreateCustomer() {

    // }

    @Test
    public void testDeleteCustomer() {

    }

    @Test
    public void testSetCart() {

    }

    @Test
    public void testSetCustomerId() {

    }

    @Test
    public void testSetEmail() {

    }

    @Test
    public void testSetPassword() {

    }

    @Test
    public void testSetProducts() {

    }
}