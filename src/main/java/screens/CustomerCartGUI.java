package screens;

import javax.swing.*;
import javax.xml.crypto.KeySelector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

//    public CustomerCartGUI(){
//
//    }
//    should take in a cart which takes in customer id and cart products
//    public CustomerCartGUI(Cart cart){
//
//    }

//    ActionListener actionListener = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (e.getSource() == purchaseAllButton) {
//                for(int i = 0; i < cart.getProducts(); i++) {
//                    customer.getProducts().add(cart.getProducts().get(i));
//                    cart.removeProduct(cart.getProducts().get(i));
//                }
//            }
//            if (e.getSource() == returnHomeButton) {
//                try {
//                    frame.dispose();
//                    SwingUtilities.invokeLater(new CustomerMarketplaceGUI());
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            if (e.getSource() == logOutButton) {
//                frame.dispose();
//                SwingUtilities.invokeLater(new LoginGUI());
//            }
//        }
//    };
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
//
//        returnHomeButton = new JButton("Return Home");
//        returnHomeButton.addActionListener(actionListener);
//        logOutButton = new JButton("Log Out");
//        logOutButton.addActionListener(actionListener);
//
//        JPanel bottomPanel = new JPanel();
//        bottomPanel.add(returnHomeButton);
//        bottomPanel.add(new JLabel("     "));
//        bottomPanel.add(logOutButton);
//        content.add(bottomPanel, BorderLayout.SOUTH);
//
//        JPanel middlePanel = new JPanel();
//        middlePanel.setLayout(new GridBagLayout());
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(5, 5, 5, 5);
//
//        middlePanel.add(new JLabel("Customer ID: " /* + customer.getId()*/), gbc);
//        gbc.gridy++;
//        middlePanel.add(new JLabel("Products"), gbc);
//        gbc.gridy++;
//        for (int i = 0; i < cart.getProducts(); i++) {
//            JLabel label = new JLabel(("<html>" +
//                    "<div style='text-align: center;'>" +
//                    "<div>" + "Product Id: " + cart.getProducts().get(i).getProductId() + "</div>" +
//                    "<div>" + "Product Name: $" + cart.getProducts().get(i).getName() + "</div>" +
//                    "</div>" +
//                    "</html>"), gbc);
//            Product product = new Product(cart.getProducts().get(i));
//            JButton removeButton = new JButton("Remove");
//            middlePanel.add(removeButton);
//            gbc.gridx++;
//            JButton purchaseButton = new JButton("Purchase");
//            middlePanel.add(purchaseButton);
//            gbc.gridy++;
//
//            removeButton.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    cart.removeFromCart(product);
//                }
//            });
//
//            purchaseButton.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    customer.getProducts().add(product);
//                }
//            });
//        }
//        purchaseAllButton = new JButton("Purchase All");
//        purchaseAllButton.addActionListener(actionListener);
//        middlePanel.add(purchaseAllButton, gbc);
//        content.add(middlePanel, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new CustomerCartGUI());
    }
}
