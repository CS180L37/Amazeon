
public class Product {

    private int sellerid;
    private int quantity;
    private String name;
    private int[] storeIDs;
    private String description;
    private double price;
    private Seller seller;

    Customer cls = new Customer();

    public int getSellerid() {
        return sellerid;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public int[] getStoreIDs() {
        return storeIDs;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Seller getSeller() {
        return seller;
    }

    public Customer getCls() {
        return cls;
    }

    public Product(int sellerid, int quantity, String name, int[] storeIDs, String description, double price) {
        this.sellerid = sellerid;
        this.quantity = quantity;
        this.name = name;
        this.storeIDs = storeIDs;
        this.description = description;
        this.price = price;
    }

    public Seller getSeller() {
        return seller;
    }
    public Double getPrice() {
        return price;
    }

    public void displayProductPage() {
        System.out.println("The quantiy is: ", quantity);
        System.out.println("The description is: ", description);
        System.out.println("Previously purchased products are: ", cls.productsPurchased);
    }
}
