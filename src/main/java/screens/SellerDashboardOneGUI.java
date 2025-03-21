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
import org.checkerframework.checker.units.qual.C;
import utils.fields;

public class SellerDashboardOneGUI extends JComponent implements Runnable {

    // sorts customers by number of items they have purchased
    JFrame frame;

    JButton sortTwoButton;
    JButton returnHomeButton;
    JButton logOutButton;

    Seller seller;

    public SellerDashboardOneGUI(Seller seller) {
        this.seller = seller;
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == sortTwoButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new SellerDashboardTwoGUI(seller));
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

        frame.setTitle("Dashboard Sort Customers by Number of Items Purchased");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        sortTwoButton = new JButton("Sort By Sales of Products");
        sortTwoButton.addActionListener(actionListener);
        sortTwoButton.setPreferredSize(new Dimension(100, 20));
        returnHomeButton = new JButton("Return Home");
        returnHomeButton.addActionListener(actionListener);
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(actionListener);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Dashboard"));
        topPanel.add(sortTwoButton);
        content.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(returnHomeButton);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(logOutButton);
        content.add(bottomPanel, BorderLayout.SOUTH);

        // sort section of dashboard code

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        try {
            ArrayList<Customer> sortedCust = Customer.sortNonDeletedCustomersByNumProducts();
            if (sortedCust != null) {
                for (int i = 0; i < sortedCust.size(); i++) {
                    JLabel customerName = new JLabel("Customer Email: " + sortedCust.get(i).getEmail());

                    middlePanel.add(customerName, gbc);
                    gbc.gridy++;
                }
            } else {
                sortedCust = Customer.sortNonDeletedCustomers(fields.customerId, Query.Direction.ASCENDING);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        content.add(middlePanel, BorderLayout.CENTER);

    }
    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(new SellerDashboardOneGUI());
    // }
}
