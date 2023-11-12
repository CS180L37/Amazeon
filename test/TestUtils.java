
/// The mock data used for all the tests
/// NOTE: I'm not testing any getters and setters unless they have additional functionality, 
/// e.g. getById, setById (this includes the getters w/ generic typing) 
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;

public class TestUtils {
    // Dashboard and market are parent classes, so general usecases methods are
    // provided for child classes
    // to test the inherited functionality
    // Not used for actual tests, but mutable copies are made
    public Cart nintendoFanBoyCart;
    public Cart gamerCart;
    public CustomerDashboard nintendoFanBoyDashboard;
    public CustomerMarket nintendoFanBoyMarket;
    public CustomerDashboard gamerDashboard;
    public CustomerMarket gamerMarket;
    public Customer nintendoFanBoy;
    public Customer gamer;
    public Sale wiiToNintendoFanBoy;
    public Sale wiiToGamer;
    public Sale xboxToGamer;
    // Products
    public Product wii;
    public Product xbox;
    public Product playstation;
    public SellerDashboard nintendoDashboard;
    public SellerDashboard microsoftDashboard;
    public SellerDashboard sonyDashboard;
    public SellerMarket nintendoMarket;
    public SellerMarket microsoftMarket;
    public SellerMarket sonyMarket;
    public Seller nintendo;
    public Seller microsoft;
    public Seller sony;
    public Store gamestop;
    public Store bestBuy;
    public User user;
    public final PrintStream originalOutput;
    public final InputStream originalSysin;
    public ByteArrayOutputStream testOut;

    // Initialize what needs to be initialized
    public TestUtils() {
        this.originalOutput = System.out;
        this.originalSysin = System.in;
    }

    // Reset the data after each test
    // Should id be autoassigned?
    // Can some of the parameters here be automatically assigned?
    public void reset() {
        ArrayList<Integer> storeIds = new ArrayList<Integer>();
        storeIds.add(0);
        storeIds.add(1);
        // Products
        this.wii = new Product(0, "Wii", 2, "A Nintendo Game Console", 100.00, 0, storeId);
        this.xbox = new Product(1, "Xbox360", 1, "A Microsoft Game Console", 299.99, 1, storeId);
        this.playstation = new Product(2, "PS3", 1, "A Sony Game Console", 400.00, 2, storeId);
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(this.wii);
        products.add(this.xbox);
        products.add(this.playstation);
        ArrayList<Product> nintendoFanBoyWiis = new ArrayList<Product>();
        nintendoFanBoyWiis.add(this.wii);
        nintendoFanBoyWiis.add(this.wii);
        // Cart
        this.nintendoFanBoyCart = new Cart(0, null); // No products in cart, already has a wii
        this.gamerCart = new Cart(1, new ArrayList<Product>(products.subList(2, 3))); // [playstation] in cart
        // Customers
        // [wii, wii]
        this.nintendoFanBoy = new Customer(0, nintendoFanBoyWiis, nintendoFanBoyCart);
        // [wii, xbox]
        this.gamer = new Customer(1, new ArrayList<Product>(products.subList(0, 2)), nintendoFanBoyCart);
        ArrayList<Customer> customers = new ArrayList<Customer>();
        customers.add(this.nintendoFanBoy);
        customers.add(this.gamer);
        // Stores
        this.gamestop = new Store("GameStop", 0, products, customers); // [nintendoFanBoy, gamer] are customers
        this.bestBuy = new Store("BestBuy", 1, new ArrayList<Product>(products.subList(1, 2)), null);
        ArrayList<Store> stores = new ArrayList<Store>();
        stores.add(this.gamestop);
        stores.add(this.bestBuy);
        // Customer utils
        this.nintendoFanBoyDashboard = new CustomerDashboard(stores, stores);
        this.nintendoFanBoyMarket = new CustomerMarket(stores, this.nintendoFanBoy, this.nintendoFanBoyDashboard);
        this.gamerDashboard = new CustomerDashboard(stores, stores);
        this.gamerMarket = new CustomerMarket(stores, this.gamer, this.gamerDashboard);
        // Sales for all previously purchased products
        this.wiiToNintendoFanBoy = new Sale(this.nintendoFanBoy, this.wii, 2);
        this.wiiToGamer = new Sale(this.gamer, this.wii, 1);
        this.xboxToGamer = new Sale(this.gamer, this.xbox, 1);
        // Seller
        this.nintendo = new Seller(0, new ArrayList<Product>(Arrays.asList(this.wii)),
                new ArrayList<Sale>(Arrays.asList(this.wiiToNintendoFanBoy)));
        this.microsoft = new Seller(1, new ArrayList<Product>(Arrays.asList(this.xbox)),
                new ArrayList<Sale>(Arrays.asList(this.xboxToGamer)));
        this.sony = new Seller(2, new ArrayList<Product>(Arrays.asList(this.playstation)), null);
        // Seller utils
        this.nintendoDashboard = new SellerDashboard(customers, new ArrayList<Product>(Arrays.asList(this.wii)));
        this.microsoftDashboard = new SellerDashboard(new ArrayList<Customer>(Arrays.asList(this.gamer)),
                new ArrayList<Product>(Arrays.asList(this.xbox)));
        this.sonyDashboard = new SellerDashboard(null, new ArrayList<Product>(Arrays.asList(this.playstation)));
        this.nintendoMarket = new SellerMarket(stores, this.nintendo, this.nintendoDashboard);
        this.microsoftMarket = new SellerMarket(stores, this.microsoft, this.microsoftDashboard);
        this.sonyMarket = new SellerMarket(stores, this.sony, this.sonyDashboard);
        // For handling working with System IO operations
        this.testOut = new ByteArrayOutputStream();
    }

    @Before
    public void setUp() {
        reset(); // Reinitialize all fields
        System.setOut(new PrintStream(this.testOut)); // Set SysOut
    }

    @After
    public void tearDown() {
        System.setOut(originalOutput);
        System.setIn(originalSysin);
    }
}
