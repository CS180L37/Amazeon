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

public class SellerEditProductGUI extends JComponent implements Runnable {
    JFrame frame;
    JComboBox<String> productNameField;
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
            if (e.getSource() == editButton) {
                Product product;
                try {
                    product = Product.getProductByName(productNameField.getSelectedItem().toString());
                    frame.dispose();
                    SwingUtilities.invokeLater(new SellerUpdateProductGUI(seller, product));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product ID!", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                }
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

        frame.setTitle("Edit Product Page");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Enter Product Name"));
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

        JLabel productIdLabel = new JLabel("Enter Product Name:");
        ArrayList<Product> products = seller.getProducts();
        ArrayList<String> productNames = new ArrayList<String>();
        if (products != null) {
            for (Product product : products) {
                productNames.add(String.valueOf(product.getName()));
            }
            String[] productIdsList = new String[products.size()];

            productNameField = new JComboBox<String>(productNames.toArray(productIdsList));
        }
        editButton = new JButton("Edit Product");
        editButton.addActionListener(actionListener);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        middlePanel.add(productIdLabel, gbc);
        gbc.gridx++;
        middlePanel.add(productNameField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        middlePanel.add(editButton, gbc);
        content.add(middlePanel, BorderLayout.CENTER);
    }
    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(new SellerEditProductGUI());
    // }
}
