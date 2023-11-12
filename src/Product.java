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
                products.add(new Product(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]),
                        data[3], Double.parseDouble(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6])));
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
                bw.write(Integer.toString(product.getProductId()) + "," + product.getName()
                        + Integer.toString(product.getQuantity()) + "," + product.getDescription() + ","
                        + Double.toString(product.getPrice()) + "," + Integer.toString(product.getSellerId()) + ","
                        + Integer.toString(product.getStoreId()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
