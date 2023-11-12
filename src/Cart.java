import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Cart {
    private int customerID;
    private ArrayList<Product> cartProducts;

    public Cart(int customerID, ArrayList<Product> cartProducts) {
        this.customerID = customerID;
        this.cartProducts = (cartProducts != null) ? cartProducts : new ArrayList<Product>();
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setCartProducts(ArrayList<Product> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public ArrayList<Product> getCartProducts() {
        return this.cartProducts;
    }

    // Adds the product to cartProducts
    public boolean addToCart(Product product) {
        return cartProducts.add(product);
    }

    // Removes the product from cartProducts
    public boolean removeFromCart(Product product) {
        return cartProducts.remove(product);
    }

    // Purchases all the products in the cart for the specific customer
    public void purchaseCart() {
        for (Product p : cartProducts) {
            Amazeon.getCustomerById(customerID).purchaseProduct(p);
            cartProducts.remove(p);
        }
    }

    // Display the customers cart
    public void display() {
        System.out.println("Customer ID: " + customerID);
        System.out.println("Cart Contents: ");

        for (Product product : cartProducts) {
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Product Name: " + product.getName());
        }
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }

    // Contains a list of all products as a parameter
    public static ArrayList<Cart> readCarts() {
        ArrayList<Cart> carts = new ArrayList<Cart>();
        try {
            BufferedReader br = Utils.createReader(Utils.DATA_DIR + Utils.CART_FILE);
            String line;
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] data = line.split(",");
                carts.add(new Cart(Integer.parseInt(data[0]),
                        (!data[1].equals(Utils.NA)) ? Amazeon.getProductByIds(Utils.splitIdsByPipe(data[1]))
                                : new ArrayList<Product>()));
            }
            return carts;
        } catch (IOException e) {
            return new ArrayList<Cart>();
        }
    }

    public static void writeCarts(ArrayList<Cart> carts) {
        try {
            BufferedWriter bw = Utils.createWriter(Utils.DATA_DIR + Utils.CART_FILE);
            for (Cart cart : carts) {

                bw.write(
                        Integer.toString(cart.getCustomerID()) + ","
                                + Utils.convertToIdString((Amazeon.getProductIds(cart.getCartProducts())).toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
