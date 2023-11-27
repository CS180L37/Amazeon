package TODOREFACTOR;

import java.util.ArrayList;

import Amazeon;

public class CustomerMarket extends Market<Customer> implements MarketInterface<Customer, Store, Store> {
    private CustomerDashboard dashboard;

    public CustomerMarket(Customer customer, ArrayList<Store> stores) {
        super(customer, stores); // Retrieve existing stores and customer
        this.dashboard = new CustomerDashboard(stores, stores, customer);
    }

    public CustomerMarket(Customer customer, ArrayList<Store> stores, CustomerDashboard dashboard) {
        super(customer, stores);
        this.dashboard = dashboard;
    }

    // Displays the list of products
    @Override
    public void displayMarketplace() {
        for (int i = 0; i < Amazeon.stores.size(); i++) {
            for (int j = 0; j < Amazeon.stores.get(i).getProducts().size(); j++) {
                System.out.println("Store Name: " + Amazeon.stores.get(i).getName()
                        + "\nProduct Name: " + Amazeon.stores.get(i).getProducts().get(j).getName()
                        + "\nProduct Price: " + Amazeon.stores.get(i).getProducts().get(j).getPrice() + "\n");
            }
        }
    }

    // When clicking on an individual product
    @Override
    public void displayProductPage(Product product) {
        System.out.println(Amazeon.getStoreById(product.getStoreId()).getName() + "\n" + product.getName() + "\n"
                + product.getDescription() + "\n" + product.getQuantity() + "\n" + product.getPrice());
        // add display of purchase history
    }

    // Display/work with the dashboard for customers
    @Override
    public void displayDashboard() {
        dashboard.displayDashboard();
    }

    public CustomerDashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(CustomerDashboard dashboard) {
        this.dashboard = dashboard;
    }

    // Sort the marketplace
    public void sort(boolean price, boolean quantityAvailable) {
        ArrayList<Product> allProducts = new ArrayList<Product>();

        if (price && !quantityAvailable) {
            for (int i = 0; i < this.getStores().size(); i++) {
                for (int j = 0; j < this.getStores().get(i).getProducts().size(); j++) {
                    allProducts.add(this.getStores().get(i).getProducts().get(j));
                }
            }

            for (int i = 0; i < allProducts.size() - 1; i++) {
                int maxIndex = i;
                for (int j = 0; i < allProducts.size(); j++) {
                    if (allProducts.get(i).getPrice() > allProducts.get(maxIndex).getPrice()) {
                        maxIndex = j;
                    }
                }

                if (maxIndex != i) {
                    Product product = allProducts.get(maxIndex);
                    allProducts.set(maxIndex, allProducts.get(i));
                    allProducts.set(maxIndex, product);
                }
            }

            // prints all products in marketplace
            for (int i = 0; i < allProducts.size(); i++) {
                System.out.println("Store Name: " + Amazeon.getStoreById(allProducts.get(i).getStoreId())
                        + "\nProduct Name: " + allProducts.get(i).getName()
                        + "\nProduct Price: " + allProducts.get(i).getPrice() + "/n/n");
            }

        } else {
            for (int i = 0; i < this.getStores().size(); i++) {
                for (int j = 0; j < this.getStores().get(i).getProducts().size(); j++) {
                    allProducts.add(this.getStores().get(i).getProducts().get(j));
                }
            }

            for (int i = 0; i < allProducts.size() - 1; i++) {
                int maxIndex = i;
                for (int j = 0; i < allProducts.size(); j++) {
                    if (allProducts.get(i).getQuantity() > allProducts.get(maxIndex).getQuantity()) {
                        maxIndex = j;
                    }
                }
                if (maxIndex != i) {
                    Product product = allProducts.get(maxIndex);
                    allProducts.set(maxIndex, allProducts.get(i));
                    allProducts.set(maxIndex, product);
                }
            }

            // prints all products in marketplace
            for (int i = 0; i < allProducts.size(); i++) {
                System.out.println("Store Name: " + Amazeon.getStoreById(allProducts.get(i).getStoreId()).getName()
                        + "\nProduct Name: " + allProducts.get(i).getName()
                        + "\nProduct Price: " + allProducts.get(i).getPrice() + "/n/n");
            }
        }
    }

    // Search the marketplace by name, storeId, or description
    public ArrayList<Product> search(String name, String storeId, String description) {
        ArrayList<Product> relevantProducts = new ArrayList<Product>();
        if (name != null && storeId == null && description == null) {
            for (int i = 0; i < this.getStores().size(); i++) {
                for (int j = 0; j < this.getStores().get(i).getProducts().size(); j++) {
                    if (this.getStores().get(i).getProducts().get(j).getName().contains(name)) {
                        relevantProducts.add(this.getStores().get(i).getProducts().get(j));
                    }
                }
            }
        } else if (name == null && storeId != null && description == null) {
            for (int i = 0; i < this.getStores().size(); i++) {
                for (int j = 0; j < this.getStores().get(i).getProducts().size(); j++) {
                    if (String.valueOf(this.getStores().get(i).getProducts().get(j).getStoreId()).contains(storeId)) {
                        relevantProducts.add(this.getStores().get(i).getProducts().get(j));
                    }
                }
            }
        } else {
            for (int i = 0; i < this.getStores().size(); i++) {
                for (int j = 0; j < this.getStores().get(i).getProducts().size(); j++) {
                    if (this.getStores().get(i).getProducts().get(j).getDescription().contains(description)) {
                        relevantProducts.add(this.getStores().get(i).getProducts().get(j));
                    }
                }
            }
        }

        for (int i = 0; i < relevantProducts.size(); i++) {
            System.out.println("Store Name: " + Amazeon.getStoreById(relevantProducts.get(i).getStoreId()).getName()
                    + "Product Name: " + relevantProducts.get(i).getName()
                    + "Product Price: " + relevantProducts.get(i).getPrice());
        }
        return relevantProducts;
    }

    // A utility method for displaying the users cart; just calls display() from
    // the cart class
    @Override
    public void displayCart() {
        this.getUser().getCart().display();
    }
}
