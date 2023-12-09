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

        frame.setTitle("Dashboard Sort One Page");
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

        ArrayList<Customer> sortedCust = new ArrayList<Customer>();
        for (int i = 0; i < seller.getSales().size(); i++) {
            Customer customer;
            try {
                customer = Customer.getCustomerById(seller.getSales().get(i).getCustomerId());
                sortedCust.add(customer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 0; i < sortedCust.size() - 1; i++) {
            int minIndex = i;
            for (int j = 0; j < sortedCust.size(); j++) {
                if (sortedCust.get(j).getProducts().size() < sortedCust.get(minIndex).getProducts().size()) {
                    minIndex = j;
                }
            }

            Customer customer = sortedCust.get(minIndex);
            sortedCust.set(minIndex, sortedCust.get(i));
            sortedCust.set(minIndex, customer);
        }

        for (int i = 0; i < sortedCust.size(); i++) {
            JLabel customerName = new JLabel("Customer " + i + " ID: " + sortedCust.get(i).getCustomerId());

            middlePanel.add(customerName, gbc);
            gbc.gridy++;
        }
        content.add(middlePanel, BorderLayout.CENTER);

    }
    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(new SellerDashboardOneGUI());
    // }
}
