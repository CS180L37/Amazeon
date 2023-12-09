package screens;

import javax.swing.*;
import javax.xml.crypto.KeySelector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import models.Cart;
import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import models.Store;

public class CustomerCartGUI extends JComponent implements Runnable{
    JFrame frame;
    JButton purchaseAllButton;
    JButton returnHomeButton;
    JButton logOutButton;

    Customer customer;
    Cart cart;
    ArrayList<Sale> sales;
    public CustomerCartGUI(Customer customer){
        this.customer = customer;
        cart = customer.getCart();
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == purchaseAllButton) {
                for(int i = 0; i < cart.getCartProducts().size(); i++) {
                    try {
                        Product product = cart.getCartProducts().get(i);
                        product.setQuantity(product.getQuantity() - 1);
                        //1) remove from cart
                        cart.removeFromCart(product);
                        //2) add to customer's product list
                        ArrayList<Product> newProducts = customer.getProducts();
                        newProducts.add(product);
                        customer.setProducts(newProducts);
                        //add to sales list of seller
                        Seller seller = Seller.getSellerById(product.getSellerId());
                        Sale sale = Sale.createSale(product.getPrice(), Sale.getNextSaleId(), customer.getCustomerId(), product.getProductId(), 1);
                        ArrayList<Sale> newSales = seller.getSales();
                        newSales.add(sale);
                        seller.setSales(newSales);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            if (e.getSource() == returnHomeButton) {
                try {
                    frame.dispose();
                    SwingUtilities.invokeLater(new CustomerMarketplaceGUI(customer));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == logOutButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new LoginGUI());
            }
        }
    };
    public void run() {
        frame = new JFrame();

        Container content = frame.getContentPane(); //creates a container in which things go for that frame
        content.setLayout(new BorderLayout()); //sets layout nicely

        //sets frame style and displays frame
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Cart"));
        content.add(topPanel, BorderLayout.NORTH);

        returnHomeButton = new JButton("Return Home");
        returnHomeButton.addActionListener(actionListener);
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(actionListener);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(returnHomeButton);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(logOutButton);
        content.add(bottomPanel, BorderLayout.SOUTH);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        middlePanel.add(new JLabel("Customer ID: " + customer.getCustomerId()), gbc);
        gbc.gridy++;
        if(cart.getCartProducts().size() > 0){
            middlePanel.add(new JLabel("Products"), gbc);
            for (int i = 0; i < cart.getCartProducts().size(); i++) {
                System.out.println(cart.getCartProducts().size());
                gbc.gridy++;
                JLabel label = new JLabel("<html>" +
                        "<div style='text-align: center;'>" +
                        "<div>" + "Product Id: " + cart.getCartProducts().get(i).getProductId() + "</div>" +
                        "<div>" + "Product Name: " + cart.getCartProducts().get(i).getName() + "</div>" +
                        "</div>" +
                        "</html>");
                middlePanel.add(label,gbc);
                Product product = cart.getCartProducts().get(i);
                gbc.gridy++;
                JButton removeButton = new JButton("Remove");
                middlePanel.add(removeButton, gbc);
                gbc.gridy++;
                JButton purchaseButton = new JButton("Purchase");
                middlePanel.add(purchaseButton, gbc);

                removeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            cart.removeFromCart(product);
                            JOptionPane.showMessageDialog(null, "Removed From Cart!", "Add To Cart", JOptionPane.INFORMATION_MESSAGE);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

                purchaseButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String[] options = new String[product.getQuantity()];
                            for(int i = 0; i < options.length; i++) {
                                options[i] = String.valueOf(i + 1);
                            }
                            int numPurchase = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Select quantity ", "Quantity Form",
                                    JOptionPane.PLAIN_MESSAGE, null, options, null));
                            product.setQuantity(product.getQuantity() - numPurchase);
                            //1) remove from cart
                            cart.removeFromCart(product);
                            //2) add to customer's product list
                            ArrayList<Product> newProducts = customer.getProducts();
                            newProducts.add(product);
                            customer.setProducts(newProducts);
                            //add to sales list of seller
                            Seller seller = Seller.getSellerById(product.getSellerId());
                            Sale sale = Sale.createSale(product.getPrice(), Sale.getNextSaleId(), customer.getCustomerId(), product.getProductId(), numPurchase);
                            ArrayList<Sale> newSales = seller.getSales();
                            newSales.add(sale);
                            seller.setSales(newSales);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
            }
            gbc.gridy++;
            purchaseAllButton = new JButton("Purchase All");
            purchaseAllButton.addActionListener(actionListener);
            middlePanel.add(purchaseAllButton, gbc);
        } else {
            middlePanel.add(new JLabel("No Products In Your Cart"), gbc);
        }
        content.add(middlePanel, BorderLayout.CENTER);
    }
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new CustomerCartGUI());
//    }
}
