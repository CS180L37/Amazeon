package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellerSalePage extends JComponent implements Runnable {
    JFrame frame;
    String storeName;
    String productName;
    String customerEmail;
    int customerId;
    double revenueFromSale;
    JButton returnHomeButton;
    JButton logOutButton;

//    public SellerSalePage(Sale sale) {
//        Product product = getProductById(sale.getProductId());
//        Store store = getStoreById(product.getStoreId());
//        Customer customer = getCustomerById)(sale.getCustomerId());
//        storeName = store.getName();
//        productName = product.getName();
//        customerId = sale.getCustomerId();
//        customerEmail = customer.getEmail();
//        revenueFromSale = sale.getCost();
//    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == logOutButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new LoginGUI());
            }
            if(e.getSource() == returnHomeButton) {
                try {
                    frame.dispose();
                    SwingUtilities.invokeLater(new SellerMarketplaceGUI());
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

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Sale Information"));
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

//        JLabel storename = new JLabel("Store Name: " + storeName);
//        JLabel productname = new JLabel("Product Name: "+ productName);
//        JLabel customerid = new JLabel("Customer ID: " + String.valueOf(customerId));
//        JLabel customeremail = new JLabel("Customer Email: " + customerEmail);
//        JLabel revenuefromsale = new JLabel("Revenue From Sale: " + String.valueOf(revenueFromSale));
//
//        JPanel middlePanel = new JPanel();
//        middlePanel.setLayout(new GridBagLayout());
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(5,5,5,5);
//
//        middlePanel.add(storename, gbc);
//        gbc.gridy++;
//        middlePanel.add(productname, gbc);
//        gbc.gridy++;
//        middlePanel.add(customerid, gbc);
//        gbc.gridy++;
//        middlePanel.add(customeremail, gbc);
//        gbc.gridy++;
//        middlePanel.add(revenuefromsale, gbc);
//
//        content.add(middlePanel, BorderLayout.CENTER);
    }
//    public static void main(String[] args) {
//        Sale sale = new Sale(...);
//        SwingUtilities.invokeLater(new SellerSalePage(sale));
//    }
}