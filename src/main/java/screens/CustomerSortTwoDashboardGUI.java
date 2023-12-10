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

public class CustomerSortTwoDashboardGUI extends JComponent implements Runnable {
    // sorts stores by products purchased by this particular customer
    JFrame frame;
    JButton sortByProductsSoldButton;
    JButton returnHomeButton;
    JButton logOutButton;

    Customer customer;
    ArrayList<Store> stores;

    public CustomerSortTwoDashboardGUI(Customer customer) throws IOException {
        this.customer = customer;
        stores = Store.sortNonDeletedStores(fields.storeId, Query.Direction.ASCENDING);

    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == sortByProductsSoldButton) {
                try {
                    frame.dispose();
                    SwingUtilities.invokeLater(new CustomerSortOneDashboardGUI(customer));
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
        topPanel.add(new JLabel("Dashboard: By Number of Products Purchased   "));
        sortByProductsSoldButton = new JButton("Sort By Products Sold");
        sortByProductsSoldButton.addActionListener(actionListener);
        topPanel.add(sortByProductsSoldButton);
        content.add(topPanel, BorderLayout.NORTH);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 0);

        ArrayList<Store> sortedStores;
        try {
            sortedStores = Seller.sortStoresBySales();
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
    // SwingUtilities.invokeLater(new CustomerSortTwoDashboardGUI());
    // }
}
