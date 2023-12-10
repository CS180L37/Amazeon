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

public class CustomerSortOneDashboardGUI extends JComponent implements Runnable {
    // sorts stores by products sold
    JFrame frame;
    JButton sortByProductsPurchasedButton;
    JButton returnHomeButton;
    JButton logOutButton;

    Customer customer;
    ArrayList<Store> stores;

    public CustomerSortOneDashboardGUI(Customer customer) throws IOException {
        this.customer = customer;
        stores = Store.sortNonDeletedStores(fields.storeId, Query.Direction.ASCENDING);
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == sortByProductsPurchasedButton) {
                try {
                    frame.dispose();
                    SwingUtilities.invokeLater(new CustomerSortTwoDashboardGUI(customer));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
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
        topPanel.add(new JLabel("Dashboard Stores By Number of Products Sold"));
        sortByProductsPurchasedButton = new JButton("Sort By Products Purchased");
        sortByProductsPurchasedButton.addActionListener(actionListener);
        topPanel.add(sortByProductsPurchasedButton);
        content.add(topPanel, BorderLayout.NORTH);

        // sorting
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 0);

        ArrayList<Store> sortedStores;
        try {
            sortedStores = Store.sortStoresByUserPurchased(customer.getCustomerId());
            if(sortedStores == null) {
                sortedStores = Store.sortNonDeletedStores(fields.storeId, Query.Direction.ASCENDING);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        for (int i = 0; i < sortedStores.size(); i++) {
            JLabel storeName = new JLabel((i+1) + ") " + sortedStores.get(i).getName());
            middlePanel.add(storeName, gbc);
            gbc.gridy++;
        }
        content.add(middlePanel, BorderLayout.CENTER);

        returnHomeButton = new JButton("Return Home");
        returnHomeButton.addActionListener(actionListener);
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(actionListener);
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(returnHomeButton);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(logOutButton);
        content.add(bottomPanel, BorderLayout.SOUTH);

    }
    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(new CustomerSortOneDashboardGUI());
    // }
}