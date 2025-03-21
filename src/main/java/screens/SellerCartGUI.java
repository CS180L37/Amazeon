package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import com.google.cloud.firestore.Query;
import models.Cart;
import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import models.Store;
import utils.fields;

public class SellerCartGUI extends JComponent implements Runnable {
    JFrame frame;
    JButton returnHomeButton;
    JButton logOutButton;

    Seller seller;

    ArrayList<Sale> sales;

    public SellerCartGUI(Seller seller) {
        this.seller = seller;
        sales = seller.getSales();
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == logOutButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new LoginGUI());
            }
            if (e.getSource() == returnHomeButton) {
                try {
                    frame.dispose();
                    SwingUtilities.invokeLater(new SellerMarketplaceGUI(seller));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
    };

    public void run() {
        frame = new JFrame();
        try {
            frame.setIconImage(javax.imageio.ImageIO.read(new java.io.File("src/main/resources/logo.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

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
        gbc.insets = new Insets(5,5,5,5);

        ArrayList<Customer> customers;
        try {
            customers = Customer.sortNonDeletedCustomers(fields.customerId, Query.Direction.ASCENDING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < customers.size(); i++) {
            ArrayList<Product> cartProducts = customers.get(i).getCart().getCartProducts();
            if (cartProducts.size() <= 0) {
                // JLabel label = new JLabel("Nothing in cart currently");
                // middlePanel.add(label);
            } else {
                for (int j = 0; j < cartProducts.size(); j++) {
                    try {
                        JLabel label = new JLabel("<html>" +
                                "<div style='text-align: center;'>" +
                                "<div>" + "Customer ID: " + customers.get(i).getCustomerId() + "</div>" +
                                "<div>" + " " + "</div>" +
                                "<div>" + " " + "</div>" +
                                "<div>" + "Store Name: "
                                + Store.getStoreById(cartProducts.get(j).getStoreId()).getName() + "</div>" +
                                "<div>" + " " + "</div>" +
                                "<div>" + "Product Price: $" + cartProducts.get(j).getPrice() + "0" + "</div>" +
                                "<div>" + " " + "</div>" +
                                "<div>" + "Product Description: " + cartProducts.get(j).getDescription() + "</div>"
                                + "<div>" + " " + "</div>" +
                                "</div>" +
                                "</html>");
//                        label.setPreferredSize(new Dimension(250, 200));
                        middlePanel.add(label, gbc);
                        gbc.gridy++;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        JScrollPane scrollPane = new JScrollPane(middlePanel);

        // Set preferred size of the scroll pane
        scrollPane.setPreferredSize(new Dimension(200, 300));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        content.add(scrollPane, BorderLayout.CENTER); // adds the scroll bar to the container
    }
    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(new SellerCartGUI());
    // }
}
