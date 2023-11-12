import java.util.ArrayList;

public class CustomerMarket extends Market<Customer> implements MarketInterface<Customer, Store, Store> {
    private Dashboard<Store, Store> dashboard;

    public CustomerMarket(Customer customer, ArrayList<Store> stores) {
        super(customer, stores); // Retrieve existing stores and customer
        this.dashboard = new Dashboard<Store, Store>(stores, stores);
    }

    public CustomerMarket(Customer customer, ArrayList<Store> stores, Dashboard<Store, Store> dashboard) {
        super(customer, stores);
        this.dashboard = dashboard;
    }

    // Displays the list of products
    @Override
    public void displayMarketplace() {
        for(int i  = 0; i < getStores().size(); i++){
            for(int j = 0; j < getStores().get(i).getProducts().size(); j++){
                System.out.println("Store Name: " + getStores().get(i).getName()
                        + "\nProduct Name: " + getStores().get(i).getProducts().get(j).getName()
                        + "\nProduct Price: " + getStores().get(i).getProducts().get(j).getPrice() + "\n");
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'displayMarketplace'");
    }

    // When clicking on an individual product
    @Override
    public void displayProductPage(Product product) {
        System.out.println(Amazeon.getStoreById(product.getStoreId()) + "\n" + product.getName() + "\n"
                + product.getDescription() + "\n" + product.getQuantity() + "\n" + product.getPrice());
        //add display of purchase history
        throw new UnsupportedOperationException("Unimplemented method 'displayProductPage'");
    }

    // Display/work with the dashboard for customers
    @Override
    public void displayDashboard() {
        System.out.println("Would you like to sort the dashboard by products purchased (y) or number of products sold (n)?");
        int sortOption = Utils.yesOrNoToInt(Utils.SCANNER.nextLine());

        //sorts by products purchased
        if(sortOption == 1) {
            ArrayList<Product> sortedProducts = new ArrayList<Product>();
            for(int i = 0; i < this.getUser().getProducts().size(); i++){
                sortedProducts.add(this.getUser().getProducts().get(i));
            }

            for(int i = 0; i < this.getUser().getProducts().size() - 1; i++) {
                int maxIndex = i;
                for(int j = 0; i < this.getUser().getProducts().size(); j++){
                    if(this.getUser().getProducts().get(i).getQuantity() > this.getUser().getProducts().get(maxIndex).getQuantity()){
                        maxIndex = j;
                    }
                }
                if(maxIndex != i){
                    Product product = this.getUser().getProducts().get(maxIndex);
                    this.getUser().getProducts().set(maxIndex, this.getUser().getProducts().get(i));
                    this.getUser().getProducts().set(maxIndex, product);
                }
            }

            //printing the array

            for(int i = 0; i < sortedProducts.size(); i++){
                System.out.println(sortedProducts.get(i) + "\n");
            }

        } else {
            ArrayList<Product> sortedProducts = new ArrayList<Product>();
            for(int i = 0; i < this.getUser().getProducts().size(); i++){
                sortedProducts.add(this.getUser().getProducts().get(i));
            }
            //Product --> seller ids
            //sellerId --> getSellerbyID --> gets Seller instance
            //Seller --> list of sales
            //list of sales --> each sale has a product --> storeID & product quantity (store product quantity in var)
            //storeID --> getStorebyID --> gets an instance of SStore
            //market --> list of stores --> if SStore equals store[i] --> store[i]'s number of products sold += product quantity
            //sort the stores by products sold

            ArrayList<Integer> numProductsSold = new ArrayList<Integer>();

            for(int i = 0; i < getStores().size(); i++){
                int quantity = 0;
                for(int j = 0; j < getStores().get(i).getProducts().size(); j++){
                    int sellerId = getStores().get(i).getProducts().get(j).getSellerId();
                    Seller seller = Seller.getSellerById(sellerId);
                    for(int k = 0; k < seller.getSales().size(); k++){
                        quantity += seller.getSales().get(i).getProduct().getQuantity();
                    }
                }
                numProductsSold.set(i, quantity);
            }

            for(int i = 0; i < numProductsSold.size() - 1; i++) {
                int maxIndex = i;
                for(int j = 0; j < numProductsSold.size(); j++){
                    if(numProductsSold.get(i) > numProductsSold.get(maxIndex)){
                        maxIndex = j;
                    }
                }
                if(maxIndex != i){
                    int productsSold = numProductsSold.get(maxIndex);
                    numProductsSold.set(maxIndex, numProductsSold.get(i));
                    numProductsSold.set(maxIndex, productsSold);

                    Product product = this.getUser().getProducts().get(maxIndex);
                    this.getUser().getProducts().set(maxIndex, this.getUser().getProducts().get(i));
                    this.getUser().getProducts().set(maxIndex, product);
                }
            }

            //printing the array
            for(int i = 0; i < sortedProducts.size(); i++){
                System.out.println(sortedProducts.get(i) + "\n");
            }

        }
        throw new UnsupportedOperationException("Unimplemented method 'displayDashboard'");
    }

    @Override
    public Dashboard<Store, Store> getDashboard() {
        return dashboard;
    }

    @Override
    public void setDashboard(Dashboard<Store, Store> dashboard) {
        this.dashboard = dashboard;
    }

    // Sort the marketplace
    public void sort(boolean price, boolean quantityAvailable) {
        
        throw new UnsupportedOperationException("Unimplemented method 'sort'");
    }

    // Search the marketplace by name, storeId, or description
    public ArrayList<Product> search(String name, String storeId, String description) {
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    // A utility method for displaying the users cart; just calls display() from
    // the cart class
    @Override
    public void displayCart() {
        this.getUser().getCart().display();
    }
}
