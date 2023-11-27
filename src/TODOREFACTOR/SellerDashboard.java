package TODOREFACTOR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import Amazeon;

public class SellerDashboard extends Dashboard<Customer, Product> implements DashboardInterface<Customer, Product> {

    public SellerDashboard() {
        super();
    }

    public SellerDashboard(ArrayList<Customer> customers, ArrayList<Product> products) {
        super(customers, products);
    }

    // Sort customers by number of items purchased
    @Override
    public ArrayList<Customer> sort1() {
        ArrayList<Customer> sortedCust = new ArrayList<Customer>();
        for (int i = 0; i < getData1().size(); i++) {
            sortedCust.add(getData1().get(i));
        }

        for (int i = 0; i < sortedCust.size() - 1; i++) {
            int maxIndex = i;
            for (int j = 0; j < sortedCust.size(); j++) {
                if (sortedCust.get(j).getProducts().size() > sortedCust.get(maxIndex).getProducts().size()) {
                    maxIndex = j;
                }
            }

            Customer customer = sortedCust.get(maxIndex);
            sortedCust.set(maxIndex, sortedCust.get(i));
            sortedCust.set(maxIndex, customer);

        }
        // Map<Customer, Integer> customerIntegerMap = new HashMap<>();
        // for (Sale sale : user.getSales()) {
        // if (customerIntegerMap.containsKey(sale.getCustomer())) {
        // customerIntegerMap.put(sale.getCustomer(),
        // customerIntegerMap.get(sale.getCustomer()) + 1);
        // } else {
        // customerIntegerMap.put(sale.getCustomer(), 1);
        // }
        // }
        // for (Map.Entry<Customer, Integer> entry : customerIntegerMap.entrySet())
        // System.out.println("Key = " + entry.getKey() +
        // ", Value = " + entry.getValue());
        // ArrayList<Map.Entry<Customer, Integer>> keyList = new
        // ArrayList<>(customerIntegerMap.entrySet());
        // keyList.sort(Comparator.comparing(Map.Entry::getValue));
        // Map<Customer, Integer> sortedCustomerIntegerMap = new LinkedHashMap<>();
        //
        // for (Map.Entry<Customer, Integer> key : keyList) {
        // sortedCustomerIntegerMap.put(key.getKey(), key.getValue());
        // }

        // another way
        // Customer[] indicesCIM = new Customer[customerIntegerMap.size()];
        // for (int i = 0; i < customerIntegerMap.size() - 1; i++) {
        // for (int j = 0; j < customerIntegerMap.size() - 1 - i; j++) {
        // if (customerIntegerMap.get(indicesCIM[j]) >
        // customerIntegerMap.get(indicesCIM[j + 1])) {
        // Customer temp = indicesCIM[j];
        // indicesCIM[j] = indicesCIM[j + 1];
        // indicesCIM[j + 1] = temp;
        // }
        // }
        // }
        return sortedCust;
    }

    // sort products by sale
    @Override
    public ArrayList<Product> sort2() {
        int numberSales = 0;
        for (int i = 0; i < getData2().size(); i++) {
            Product product = getData2().get(i);
            for (Sale sale : Amazeon.getSellerById(getData2().get(i).getSellerId()).getSales()) {
                if (sale.getProduct().equals(product)) {
                    numberSales++;
                }
            }

            System.out.println(i + ". " + getData2().get(i) + " - " + numberSales + " Sales");
            numberSales = 0;
        }
        return new ArrayList<Product>();
    }

    @Override
    public void displayDashboard() {
        System.out.println(
                "How do you want to sort?\n1. Number of products purchased by a customer\n2. Products by number of sales\n");
        int choice; // default
        while (true) {
            try {
                choice = Utils.SCANNER.nextInt();
                Utils.SCANNER.nextLine();
                if (choice != 1 && choice != 2 && choice != 3) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid option number.");
            }
        }

        // add sorting code
        if (choice == 1) {
            sort1();
            sort2();
        } else if (choice == 2) {
            sort2();
            sort1();
        }
    }
}