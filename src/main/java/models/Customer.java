package models;

import java.util.ArrayList;

import Amazeon;
import TODOREFACTOR.User;
import utils.Utils;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Customer implements UserInterface {
    private int cartId;
    private int customerId;
    private String email;
    private String password;

    // TODO: retrieve Product by id
    private ArrayList<Product> products;

    public Customer(String email, String password) {
        if (userExists()) {

        } else {
            this.email = email;
            this.password = password;
            this.cartId = getNextId();
            this.customerId = getNextId();
            this.productIds = new ArrayList<Integer>();
        }
    }

    public int getId() {
        if (cart == null) {
            return -1;
        }
        return cart.getCustomerID();
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

    // TODO: adapt these for backend
    public static Customer getCustomerById(int customerId) {
        for (Customer customer : Amazeon.customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return new Customer(-1, "", "", new ArrayList<>(), new Cart(-1, new ArrayList<>()));
    }

    public static ArrayList<Customer> getCustomersByIds(ArrayList<Integer> customerIds) {
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        for (int customerId : customerIds) {
            customerList.add(Amazeon.getCustomerById(customerId));
        }
        return customerList;
    }

    public static int getNextCustomerId() {
        // int customerListSize = customers.size() - 1;
        // if (customerListSize < 0) {
        // return 1;
        // }
        // return customers.get(customerListSize).getId() + 1;
        return 0;
    }

    @Override
    public String toString() {
        return "Customer {" +
                "cart=" + cart +
                '}';
    }
}
