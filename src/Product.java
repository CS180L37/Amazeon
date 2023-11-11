import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Product {
    private int sellerId;
    private int quantity;
    private String name;
    private int storeId;
    private String description;
    private double price;

    private int productID;


    public Product(int sellerId, int quantity, String name, int storeId, String description,
            double price, int productID) {
        this.sellerId = sellerId;
        this.quantity = quantity;
        this.name = name;
        this.storeId = storeId;
        this.description = description;
        this.price = price;
        this.productID = productID;
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

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public Product getProductByID(int productID){

    }

    public static ArrayList<Product> readProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            BufferedReader br = Utils.createReader(".products.csv");
            String line;
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] data = line.split(",");
                products.add(new Product(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2],
                        Integer.parseInt(data[3]), data[4], Double.parseDouble(data[5])));
            }
            return products;
        } catch (IOException e) {
            return new ArrayList<Product>();
        }
    }

    public static void writeProducts(ArrayList<Product> products) {
        throw new UnsupportedOperationException("Unimplemented method 'readProducts'");
    }
}
