import java.util.ArrayList;

public class Store {
    private int id;
    private String name;
    private ArrayList<Product> products;
    private ArrayList<Customer> customers;

    public Store(int id, String name, ArrayList<Product> products, ArrayList<Customer> customers) {
        this.name = name;
        this.products = products;
        this.id = id;
        this.customers = customers;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public ArrayList<Customer> getCustomers() {
        return this.customers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    // Contains lists of all products and customers as parameters
    public static ArrayList<Store> readStores(ArrayList<Product> products, ArrayList<Customer> customers) {
        throw new UnsupportedOperationException("Unsupported operation: 'readStores'");
    }

    public static void writeStores(ArrayList<Store> stores) {
        throw new UnsupportedOperationException("Unsupported operation: 'readStores'");
    }
}
