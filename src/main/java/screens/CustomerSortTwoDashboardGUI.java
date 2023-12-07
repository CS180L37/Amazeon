package screens;

import javax.swing.*;
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
public class CustomerSortTwoDashboardGUI extends JComponent implements Runnable{
    //sorts stores by products purchased by this particular customer
    JFrame frame;
    JButton sortByProductsSoldButton;
    JButton returnHomeButton;
    JButton logOutButton;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == sortByProductsSoldButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new CustomerSortOneDashboardGUI());
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
    public void run() {
        frame = new JFrame();

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Dashboard\nStores By Number of Products Purchased"));
        content.add(topPanel, BorderLayout.NORTH);

        sortByProductsSoldButton = new JButton("Sort By Products Sold");
        sortByProductsSoldButton.addActionListener(actionListener);
        JPanel eastPanel = new JPanel();
        eastPanel.add(sortByProductsSoldButton);
        content.add(eastPanel, BorderLayout.EAST);

        //sorting
//        JPanel middlePanel = new JPanel();
//        middlePanel.setLayout(new GridBagLayout());
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(5, 5, 5, 0);
//
//        ArrayList<Integer> numPurchasedEachStore = new ArrayList<Integer>();
//
//        ArrayList<Store> sortedStores = new ArrayList<Store>();
//        for (int i = 0; i < this.getData2().size(); i++) {
//            sortedStores.add(this.getData2().get(i));
//        }
//
//        ArrayList<Product> products = new ArrayList<Product>();
//        for (int i = 0; i < customer.getProducts().size(); i++) {
//            products.add(customer.getProducts().get(i));
//        }
//
//        for (int i = 0; i < this.getData2().size(); i++) {
//            int numProductsPurchased = 0;
//            for (Product product : products) {
//                if (product.getStoreId() == this.getData2().get(i).getId()) {
//                    numProductsPurchased += product.getQuantity();
//                }
//            }
//            numPurchasedEachStore.add(i, numProductsPurchased);
//        }
//
//  // sorts stores
//        for (int i = 0; i < numPurchasedEachStore.size() - 1; i++) {
//            int maxIndex = i;
//            for (int j = 0; j < numPurchasedEachStore.size(); j++) {
//                if (numPurchasedEachStore.get(j) > numPurchasedEachStore.get(maxIndex)) {
//                    maxIndex = j;
//                }
//            }
//
//            int productsPurchased = numPurchasedEachStore.get(maxIndex);
//            numPurchasedEachStore.set(maxIndex, numPurchasedEachStore.get(i));
//            numPurchasedEachStore.set(maxIndex, productsPurchased);
//
//            Store store = sortedStores.get(maxIndex);
//            sortedStores.set(maxIndex, sortedStores.get(i));
//            sortedStores.set(maxIndex, store);
//        }
//
//        for(int i = 0; i < sortedStores.size(); i++){
//            JLabel storeName = new JLabel(sortedStores.get(i).getName() + " -- Num Products Sold: " + numPurchasedEachStore.get(i));
//            middlePanel.add(storeName, gbc);
//            gbc.gridy++;
//        }
//        content.add(middlePanel, BorderLayout.CENTER);

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
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new CustomerSortTwoDashboardGUI());
    }
}
