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

public class SellerDashboardTwoGUI extends JComponent implements Runnable {
    //sorts products by number of sales
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
            if(e.getSource() == sortOneButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new SellerDashboardOneGUI(seller));
            }
            if(e.getSource() == logOutButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new LoginGUI());
            }
            if(e.getSource() == returnHomeButton) {
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
        gbc.insets = new Insets(5,5,5,5);

        ArrayList<Product> sortedProducts = new ArrayList<Product>();
        for (int i = 0; i < seller.getProducts().size(); i++) {
            sortedProducts.add(seller.getProducts().get(i));
        }

        ArrayList<Integer> numSalesPerProduct = new ArrayList<Integer>();
        for(int i = 0; i < sortedProducts.size(); i++){
            int numPurchased = 0;
            for(int j = 0 ; j < seller.getSales().size(); j++) {
                if(seller.getSales().get(j).getProductId() == sortedProducts.get(i).getProductId()) {
                    numPurchased += seller.getSales().get(j).getNumPurchased();
                } else {
                    numPurchased += 0;
                }
            }
            numSalesPerProduct.add(i, numPurchased);
        }

        for (int i = 0; i < numSalesPerProduct.size() - 1; i++) {
            int minIndex = i;
            for (int j = 0; j < numSalesPerProduct.size(); j++) {
                if (numSalesPerProduct.get(j) <
                        numSalesPerProduct.get(minIndex)) {
                    minIndex = j;
                }
            }

            Product product = sortedProducts.get(minIndex);
            int numSales = numSalesPerProduct.get(minIndex);
            sortedProducts.set(minIndex, sortedProducts.get(i));
            numSalesPerProduct.set(minIndex, numSalesPerProduct.get(i));
            sortedProducts.set(minIndex, product);
            numSalesPerProduct.set(minIndex, numSales);
        }

        for(int i = 0; i < sortedProducts.size(); i++) {
            JLabel productName = new JLabel("Product " + i + " Name: " + sortedProducts.get(i).getName() + "       Num Sales: " + numSalesPerProduct.get(i));

            middlePanel.add(productName, gbc);
            gbc.gridy++;
        }
        content.add(middlePanel, BorderLayout.CENTER);

    }
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new SellerDashboardTwoGUI());
//    }
}

