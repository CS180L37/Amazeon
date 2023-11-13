import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
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
    public static ArrayList<Store> readStores(String filepath) {
        Amazeon.sales = Sale.readSales(Utils.DATA_DIR + Utils.SALE_FILE);
        ArrayList<Store> stores = new ArrayList<Store>();
        try {
            BufferedReader br = Utils.createReader(filepath);
            String line;
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] data = line.split(",");
                stores.add(Utils.convertFromStoreString(data));
            }
            return stores;
        } catch (IOException e) {
            return new ArrayList<Store>();
        }
    }

    public static void writeStores(ArrayList<Store> stores, String filepath) {
        try {
            BufferedWriter bw = Utils.createWriter(filepath);
            for (Store store : stores) {
                bw.write(Utils.convertToStoreString(store));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
