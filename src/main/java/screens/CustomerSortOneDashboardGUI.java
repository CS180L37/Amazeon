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

public class CustomerSortOneDashboardGUI extends JComponent implements Runnable{
    //sorts stores by products sold
    JFrame frame;
    JButton sortByProductsPurchasedButton;
    JButton returnHomeButton;
    JButton logOutButton;

    Customer customer;

    public CustomerSortOneDashboardGUI(Customer customer) {
        this.customer = customer;
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == sortByProductsPurchasedButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new CustomerSortTwoDashboardGUI(customer));
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

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Dashboard\nStores By Number of Products Sold"));
        content.add(topPanel, BorderLayout.NORTH);

        sortByProductsPurchasedButton = new JButton("Sort By Products Purchased");
        sortByProductsPurchasedButton.addActionListener(actionListener);
        JPanel eastPanel = new JPanel();
        eastPanel.add(sortByProductsPurchasedButton);
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
//
//        ArrayList<Store> sortedStores = new ArrayList<Store>();
//        for (int i = 0; i < storesList.size(); i++) {
//            sortedStores.add(storesList.get(i));
//        }
//
//        ArrayList<Integer> numProductsSold = new ArrayList<Integer>();
//
//        for (int i = 0; i < storesList.size(); i++) {
//            int quantity = 0;
//            for (int j = 0; j < storesList.get(i).getProducts().size(); j++) {
//                int sellerId = storesList.get(i).getProducts().get(j).getSellerId();
//                Seller seller = Amazeon.getSellerById(sellerId);
//                for (int k = 0; k < seller.getSales().size(); k++) {
//                    quantity += seller.getSales().get(i).getProduct().getQuantity();
//                }
//            }
//            numProductsSold.set(i, quantity);
//        }
//
//        for (int i = 0; i < numProductsSold.size() - 1; i++) {
//            int maxIndex = i;
//            for (int j = 0; j < numProductsSold.size(); j++) {
//                if (numProductsSold.get(j) > numProductsSold.get(maxIndex)) {
//                    maxIndex = j;
//                }
//            }
//
//            int productsSold = numProductsSold.get(maxIndex);
//            numProductsSold.set(maxIndex, numProductsSold.get(i));
//            numProductsSold.set(maxIndex, productsSold);
//
//            Store store = sortedStores.get(maxIndex);
//            sortedStores.set(maxIndex, sortedStores.get(i));
//            sortedStores.set(maxIndex, store);
//        }
//
//        for(int i = 0; i < sortedStores.size(); i++){
//            JLabel storeName = new JLabel(sortedStores.get(i).getName() + " -- Num Products Sold: " + numProductsSold.get(i));
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
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new CustomerSortOneDashboardGUI());
//    }
}