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

public class SellerDashboardTwoGUI extends JComponent implements Runnable {
    // sorts products by number of sales
    JFrame frame;

    JButton sortOneButton;
    JButton returnHomeButton;
    JButton logOutButton;

    Seller seller;

    public SellerDashboardTwoGUI(Seller seller) {
        this.seller = seller;
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == sortOneButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new SellerDashboardOneGUI(seller));
            }
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

        frame.setTitle("Dashboard Sort One Page");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        sortOneButton = new JButton("Sort By Sales of Products");
        sortOneButton.addActionListener(actionListener);
        sortOneButton.setPreferredSize(new Dimension(100, 20));
        returnHomeButton = new JButton("Return Home");
        returnHomeButton.addActionListener(actionListener);
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(actionListener);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Dashboard"));
        topPanel.add(sortOneButton);
        content.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(returnHomeButton);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(logOutButton);
        content.add(bottomPanel, BorderLayout.SOUTH);

        // sorts by products by number of sales
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        try {
            ArrayList<Product> sortedProducts = Sale.sortProductBySales();

            if(sortedProducts != null) {
                for (int i = 0; i < sortedProducts.size(); i++) {
                    JLabel productName = new JLabel("Product " + (i+1) + " Name: " + sortedProducts.get(i).getName());

                    middlePanel.add(productName, gbc);
                    gbc.gridy++;
                }
            } else {
                sortedProducts = Product.sortNonDeletedProducts(fields.productId, Query.Direction.ASCENDING);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        content.add(middlePanel, BorderLayout.CENTER);

    }
    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(new SellerDashboardTwoGUI());
    // }
}
