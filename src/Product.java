import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Product {
    private int productId;
    private String name;
    private int quantity;
    private String description;
    private double price;
    private int sellerId;
    private int storeId;
    //public Seller(int id, ArrayList<Product> products, String email, String password, ArrayList<Sale> sales) {

    public Product(int productId, String name, int quantity, String description,
            double price, int sellerId, int storeId) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.sellerId = sellerId;
        this.storeId = storeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static ArrayList<Product> readProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            BufferedReader br = Utils.createReader(Utils.DATA_DIR + Utils.PRODUCT_FILE);
            String line;
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] data = line.split(",");
                products.add(Utils.convertFromProductString(data));
            }
            return products;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Product>();
        }
    }

    public static void writeProducts(ArrayList<Product> products) {
        try {
            BufferedWriter bw = Utils.createWriter(Utils.DATA_DIR + Utils.PRODUCT_FILE);
            for (Product product : products) {
                bw.write(Utils.convertToProductString(product));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
