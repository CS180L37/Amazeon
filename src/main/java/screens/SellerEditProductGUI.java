package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import models.Cart;
import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import models.Store;

public class SellerEditProductGUI extends JComponent implements Runnable {
    JFrame frame;
    JTextField productIdField;
    JButton editButton;
    JButton returnHomeButton;
    JButton logOutButton;

    Seller seller;

    public SellerEditProductGUI(Seller seller) {
        this.seller = seller;
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == editButton) {
                Product product;
                try {
                    product = Product.getProductById(Integer.parseInt(productIdField.getText()));
                    frame.dispose();
                    SwingUtilities.invokeLater(new SellerUpdateProductGUI(seller, product));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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

        frame.setTitle("Edit Product Page");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Enter Product Id"));
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

        JLabel productIdLabel = new JLabel("Enter Product ID:");
        productIdField = new JTextField(10);
        editButton = new JButton("Edit Product");
        editButton.addActionListener(actionListener);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5,5,5,5);

        middlePanel.add(productIdLabel, gbc);
        gbc.gridx++;
        middlePanel.add(productIdField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        middlePanel.add(editButton, gbc);
        content.add(middlePanel, BorderLayout.CENTER);
    }
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new SellerEditProductGUI());
//    }
}
