import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
public class Customer extends User implements UserInterface<Customer> {
    private Cart cart;
    // Contains id, email, password, and a list of purchased products

    public Customer(String email, String password) {
        super(Amazeon.getNextCustomerId(), new ArrayList<Product>(), email, password);
        this.cart = new Cart(this.getId(), this.getProducts());
    }

    public Customer(int id, String email, String password, ArrayList<Product> products, Cart cart) {
        super(id, products, email, password);
        this.cart = cart;
        if (this.cart == null) {
            this.cart = new Cart(-1, new ArrayList<>());
        }
    }

    // Exports customer purchase history
    @Override
    public void exportData(String filepath) {
        // Export customer data to Utils.CUSTOMER_FILE
        try {
            BufferedWriter bw = Utils.createWriter(filepath);
            // bw.write(this.getProducts());
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public int getId() {
        if (cart == null) {
            return -1;
        }
        return cart.getCustomerID();
    }

    // unnecessary method
    @Override
    public void importData(String filepath) {
        return;
    }

    // Purchases a product
    public void purchaseProduct(Product product) {
        // Add to customers products
        for (int i = 0; i < cart.getCartProducts().size(); i++) { // goes through every product in cart products
            for (int j = 0; j < Amazeon.getSellerById(cart.getCartProducts().get(i).getSellerId()).getSales()
                    .size(); j++) { // gets each product's seller id and uses seller id to get seller --> which then
                                    // gets the seller's list of sales
                if (Amazeon.getSellerById(cart.getCartProducts().get(i).getSellerId()).getSales().get(j).getProduct()
                        .getProductId() == cart.getCartProducts().get(i).getProductId()) { // uses each sale to get
                                                                                           // product's id and compares
                                                                                           // this value to cartProducts
                                                                                           // product at index i
                    Sale sale = new Sale(
                            Amazeon.getSellerById(cart.getCartProducts().get(i).getSellerId()).getSales().get(j)
                                    .getSaleId(),
                            Amazeon.getCustomerById(this.getId()), cart.getCartProducts().get(i),
                            cart.getCartProducts().get(i).getQuantity()); // purchases product
                    break;
                }
            }
        }
        // throw new UnsupportedOperationException("Unsupported operation
        // 'purchaseProduct");
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void editAccount(String email, String password) {
        if (Utils.validateEmail(email)) {
            this.setEmail(email);
        } else {
            System.out.println("Make sure to enter a valid email if you want to change your email!");
        }
        if (Utils.validatePassword(password)) {
            this.setPassword(password);
        } else {
            System.out.println("Make sure to enter a valid email if you want to change your email!");
        }
    }

    @Override
    public void deleteAccount() {
        Amazeon.customers.remove(this);
    }

    // Contains lists of all products and carts as parameters
    public static ArrayList<Customer> readCustomers(String filepath) {
        Amazeon.carts =  Cart.readCarts(Utils.DATA_DIR + Utils.CART_FILE);
        ArrayList<Customer>  customers = new ArrayList<Customer>();
        try {
            BufferedReader br = Utils.createReader(filepath);
            String line;
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] data = line.split(",");
                customers.add(Utils.convertFromCustomerString(data));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<Customer>();
    }

    public static void writeCustomers(ArrayList<Customer> customers, String filepath) {
        try {
            BufferedWriter bw = Utils.createWriter(filepath);
            for (Customer customer : customers) {
                bw.write(Utils.convertToCustomerString(customer));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cart=" + cart +
                '}';
    }
}
