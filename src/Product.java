import java.util.ArrayList;

public class Product {
    private int sellerId;
    private int quantity;
    private String name;
    private ArrayList<Integer> storeIds;
    private String description;
    private double price;

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

    public ArrayList<Integer> getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(ArrayList<Integer> storeIds) {
        this.storeIds = storeIds;
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
}
