import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

// User parent class has no methods to test
public class CustomerTest extends TestUtils {
    @Test
    public void testExportData() {
        // this.nintendoFanBoy.exportData("");
        throw new UnsupportedOperationException("Unimplemented method 'testExportData'");
    }

    @Test
    public void testEditAccount() {
        Customer customer = Amazeon.getCustomerById(0);
        customer.editAccount("newEmail@gmail.com", "newPassword");
        assertEquals(customer.getEmail(), "newEmail@gmail.com");
        assertEquals(customer.getPassword(), "newPassword");
    }

    @Test
    public void testDeleteAccount() {
        Customer customer = Amazeon.getCustomerById(0);
        customer.deleteAccount();
        assertEquals(Amazeon.getCustomerById(0),
                new Customer(-1, "", "", new ArrayList<>(), new Cart(-1, new ArrayList<>())));
    }

    @Test
    public void testPurchaseProduct() {
        Customer customer = Amazeon.getCustomerById(0);
        customer.purchaseProduct(Amazeon.getProductById(0));
        assertEquals(customer.getProducts().size(),
                new ArrayList<Product>(Arrays.asList(Amazeon.getProductById(0), Amazeon.getProductById(0),
                        Amazeon.getProductById(0))));
    }
}