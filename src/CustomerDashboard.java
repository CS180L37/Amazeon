import java.util.ArrayList;

public class CustomerDashboard extends Dashboard<Store, Store> implements DashboardInterface<Store, Store> {
    // Create a dashboard of existing Store data
    public CustomerDashboard(ArrayList<Store> data1, ArrayList<Store> data2) {
        super(data1, data2);
    }

    // 2 sort methods exist to account for seller having to sort two different data
    // types
    // Sort stores by products sold
    @Override
    public ArrayList<Store> sort1(User user) {
        ArrayList<Store> sortedStores = new ArrayList<Store>();
        for (int i = 0; i < this.getData1().size(); i++) {
            sortedStores.add(this.getData1().get(i));
        }
        // Product --> seller ids
        // sellerId --> getSellersID --> gets Seller instance
        // Seller --> list of sales
        // list of sales --> each sale has a product --> storeID & product quantity
        // (store product quantity in var)
        // storeID --> getStoreID --> gets an instance of SStore
        // market --> list of stores --> if SStore equals store[i] --> store[i]'s number
        // of products sold += product quantity
        // sort the stores by products sold

        ArrayList<Integer> numProductsSold = new ArrayList<Integer>(); // list of numProducts sold by each store

        for (int i = 0; i < this.getData1().size(); i++) {
            int quantity = 0;
            for (int j = 0; j < this.getData1().get(i).getProducts().size(); j++) {
                int sellerId = this.getData1().get(i).getProducts().get(j).getSellerId();
                Seller seller = Seller.getSellerById(sellerId);
                for (int k = 0; k < seller.getSales().size(); k++) {
                    quantity += seller.getSales().get(i).getProduct().getQuantity();
                }
            }
            numProductsSold.set(i, quantity);
        }

        for (int i = 0; i < numProductsSold.size() - 1; i++) {
            int maxIndex = i;
            for (int j = 0; j < numProductsSold.size(); j++) {
                if (numProductsSold.get(j) > numProductsSold.get(maxIndex)) {
                    maxIndex = j;
                }
            }

            int productsSold = numProductsSold.get(maxIndex);
            numProductsSold.set(maxIndex, numProductsSold.get(i));
            numProductsSold.set(maxIndex, productsSold);

            Store store = sortedStores.get(maxIndex);
            sortedStores.set(maxIndex, sortedStores.get(i));
            sortedStores.set(maxIndex, store);
        }
        return sortedStores;
        // throw new UnsupportedOperationException("Unimplemented method 'sort1'");
    }
    // Sort stores by products purchased by a customer

    @Override
    public ArrayList<Store> sort2(User user) {
        ArrayList<Integer> numPurchasedEachStore = new ArrayList<Integer>();

        // copies stores list from market in order to create a sortedStores list
        ArrayList<Store> sortedStores = new ArrayList<Store>();
        for (int i = 0; i < this.getData2().size(); i++) {
            sortedStores.add(this.getData2().get(i));
        }

        // copies products list from user class
        ArrayList<Product> products = new ArrayList<Product>();
        for (int i = 0; i < user.getProducts().size(); i++) {
            products.add(user.getProducts().get(i));
        }

        for (int i = 0; i < this.getData2().size(); i++) {
            int numProductsPurchased = 0;
            for (Product product : products) {
                if (product.getStoreId() == this.getData2().get(i).getId()) {
                    numProductsPurchased += product.getQuantity();
                }
            }
            numPurchasedEachStore.add(i, numProductsPurchased);
        }

        // sorts stores
        for (int i = 0; i < numPurchasedEachStore.size() - 1; i++) {
            int maxIndex = i;
            for (int j = 0; j < numPurchasedEachStore.size(); j++) {
                if (numPurchasedEachStore.get(j) > numPurchasedEachStore.get(maxIndex)) {
                    maxIndex = j;
                }
            }

            int productsPurchased = numPurchasedEachStore.get(maxIndex);
            numPurchasedEachStore.set(maxIndex, numPurchasedEachStore.get(i));
            numPurchasedEachStore.set(maxIndex, productsPurchased);

            Store store = sortedStores.get(maxIndex);
            sortedStores.set(maxIndex, sortedStores.get(i));
            sortedStores.set(maxIndex, store);
        }
        return sortedStores;
        // throw new UnsupportedOperationException("Unimplemented method 'sort2'");
    }

    // Displays the two lists of stores
    @Override
    public void displayDashboard(User user) {
        System.out.println(
                "Would you like to sort the dashboard by products purchased (y) or number of products sold (n)?");
        int sortOption = Utils.yesOrNoToInt(Utils.SCANNER.nextLine());

        // sorts by products purchased
        if (sortOption == 1) {
            sort1(user);
            for (int i = 0; i < sort1(user).size(); i++) {
                System.out.println("Store Name: " + sort1(user).get(i).getName() + "\n");
            }
            // sort by products sold
        } else {
            sort2(user);
            for (int i = 0; i < sort2(user).size(); i++) {
                System.out.println("Store Name: " + sort2(user).get(i).getName() + "\n");
            }
        }
        // throw new UnsupportedOperationException("Unimplemented method
        // 'displayDashboard'");
    }
}
