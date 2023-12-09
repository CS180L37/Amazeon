package screens;

import javax.swing.*;
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

public class CustomerProductPage extends JComponent implements Runnable {
    JFrame frame;

    JButton purchaseButton;//should be add to cart button
    JButton returnHomeButton;
    JButton logOutButton;

    String storeName;
    String productName;
    String description;
    double price;
    int quantity;

    String[] quantityOptions;

    public CustomerProductPage(Product product){
        storeName = "Candyyyyyy";
        productName = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        quantity = product.getQuantity();
        quantityOptions = new String[quantity];
        for(int i = 0; i < quantity; i++){
            quantityOptions[i] = String.valueOf(i+1);
        }
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == purchaseButton) {
                JOptionPane.showInputDialog(null, "Choose Quantity", "Quantity",
                        JOptionPane.PLAIN_MESSAGE, null, quantityOptions, null);
                //make sure to set right product quantity after purchase
                //make sure to add to cart
            }
            if (e.getSource() == returnHomeButton) {
                try {
                    frame.dispose();
                    SwingUtilities.invokeLater(new CustomerMarketplaceGUI());
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
    public void run(){
        frame = new JFrame("Login");

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        JLabel productInfo = new JLabel("Product Information");
        JLabel storeName = new JLabel("<html>&#8226; " + "Store Name: " + this.storeName + "</html>");
        JLabel productName = new JLabel("<html>&#8226; " + "Product Name: " + this.productName + "</html>");
        JLabel description = new JLabel("<html>&#8226; " +  "Product Description: " + this.description + "</html>");
        JLabel quantity = new JLabel("<html>&#8226; " + "Product Quantity: " + this.quantity + "</html>");
        JLabel price = new JLabel("<html>&#8226; " + "Product Price: " + this.price + "0" + "</html>");

        //setting a visible font size and spacing for labels
        Font largerFont = productInfo.getFont().deriveFont(15f); // Replace 20f with your desired font size
        productInfo.setFont(largerFont);
        storeName.setFont(largerFont);
        storeName.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Adjust spacing here
        productName.setFont(largerFont);
        productName.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Adjust spacing here
        description.setFont(largerFont);
        description.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Adjust spacing here
        quantity.setFont(largerFont);
        quantity.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Adjust spacing here
        price.setFont(largerFont);
        price.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Adjust spacing here

        JPanel topPanel = new JPanel();
        topPanel.add(productInfo);

        content.add(topPanel, BorderLayout.NORTH);

        purchaseButton = new JButton("Purchase");
        purchaseButton.addActionListener(actionListener);

        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 0);

        westPanel.add(storeName, gbc);
        gbc.gridy++;
        westPanel.add(productName, gbc);
        gbc.gridy++;
        westPanel.add(description, gbc);
        gbc.gridy++;
        westPanel.add(quantity, gbc);
        gbc.gridy++;
        westPanel.add(price, gbc);
        gbc.gridy++;
        westPanel.add(purchaseButton, gbc);

        content.add(westPanel, BorderLayout.WEST);

        //sets up a scroll bar for panel
        JScrollPane scrollPane = new JScrollPane(westPanel);
        scrollPane.setPreferredSize(new Dimension(200, 300)); // Set preferred size of the scroll pane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        content.add(scrollPane, BorderLayout.CENTER); // adds the scroll bar to the container


        JPanel eastPanel = new JPanel();

        JLabel prevPurchase = new JLabel("Previously Purchased Items");
        eastPanel.add(prevPurchase);
//        for(int i = 0; i < customer.getProducts().size(); i++){
//            JLabel label = new JLabel( "<html>" +
//                    "<div style='text-align: center;'>" +
//                    "<div>" + "Product Name: " + customer.getProducts().get(i).getName() + "</div>" +
//                    "<div>" + "StoreName: " + customer.getProducts().get(i).getQuantity() + "</div>" +
//                    "</div>" +
//                    "</html>");
//            eastPanel.add(label);
//        }

        content.add(eastPanel, BorderLayout.EAST);


        returnHomeButton = new JButton("Return Home");
        returnHomeButton.addActionListener(actionListener);
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(actionListener);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());

        bottomPanel.add(returnHomeButton);
        bottomPanel.add(new JLabel("    "));
        bottomPanel.add(logOutButton);

        content.add(bottomPanel, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
////        Product product = new Product(1, "Candy", 7, "sweet and sour",2.00, 1, 1);
////        SwingUtilities.invokeLater(new CustomerProductPage(product));
    }

}
