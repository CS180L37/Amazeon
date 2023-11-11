import java.util.ArrayList;

public class Product {
    private int sellerId;
    private int quantity;
    private String name;
    private int storeId;
    private String description;
    private double price;

    public Product(int sellerId, int quantity, String name, int storeId, String description,
            double price) {
        this.sellerId = sellerId;
        this.quantity = quantity;
        this.name = name;
        this.storeId = storeId;
        this.description = description;
        this.price = price;
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
        throw new UnsupportedOperationException("Unimplemented method 'readProducts'");
    }

    public static void writeProducts(ArrayList<Product> products) {
        throw new UnsupportedOperationException("Unimplemented method 'readProducts'");
    }
}
