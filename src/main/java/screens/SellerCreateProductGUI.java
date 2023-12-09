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

public class SellerCreateProductGUI extends JComponent implements Runnable {
    JFrame frame;
    // Text fields
    JTextField productNameField, productDescField, productPriceField, productStockField, productIDField, storeIDField,
            sellerIDField;
    JButton createProductButton;
    JButton logOutButton;
    JButton returnHomeButton;

    Seller seller;

    public SellerCreateProductGUI(Seller seller) {
        this.seller = seller;
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == createProductButton) {
                try {
                    Product product = Product.createProduct(productDescField.getText(), productNameField.getText(),
                            Double.parseDouble(productPriceField.getText()), Integer.parseInt(productIDField.getText()),
                            Integer.parseInt(productStockField.getText()), Integer.parseInt(sellerIDField.getText()),
                            Integer.parseInt(storeIDField.getText()));
                    ArrayList<Product> storeProducts = Store.getStoreById(Integer.parseInt(storeIDField.getText()))
                            .getStoreProducts();
                    storeProducts.add(product);
                    Store.getStoreById(Integer.parseInt(storeIDField.getText())).setStoreProducts(storeProducts);

                    ArrayList<Product> newProductList = seller.getProducts();
                    newProductList.add(product);
                    seller.setProducts(newProductList);
                    frame.dispose();
                    SwingUtilities.invokeLater(new SellerMarketplaceGUI(seller));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
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
        // Setting up the frame
        frame = new JFrame();
        try {
            frame.setIconImage(javax.imageio.ImageIO.read(new java.io.File("src/main/resources/logo.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setTitle("Create Product Page");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Creating labels
        JLabel productNameLabel = new JLabel("Product Name:");
        JLabel productDescLabel = new JLabel("Product Description:");
        JLabel productPriceLabel = new JLabel("Product Price:");
        JLabel productStockLabel = new JLabel("Product Stock:");
        JLabel productIDLabel = new JLabel("Product ID:");
        JLabel storeIDLabel = new JLabel("Store ID:");
        JLabel sellerIDLabel = new JLabel("Seller ID:");

        // Creating text fields
        productNameField = new JTextField(20);
        productDescField = new JTextField(20);
        productPriceField = new JTextField(20);
        productStockField = new JTextField(20);
        productIDField = new JTextField(20);
        productIDField.setEnabled(false);
        try {
            productIDField.setText(String.valueOf(Product.getNextProductId()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        storeIDField = new JTextField(20);
        sellerIDField = new JTextField(20);

        // Creating buttons
        createProductButton = new JButton("Create Product");
        createProductButton.addActionListener(actionListener);
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(actionListener);
        returnHomeButton = new JButton("Return Home");
        returnHomeButton.addActionListener(actionListener);

        // Setting layout
        JPanel topPanel = new JPanel();
        JLabel createProduct = new JLabel("Create Product");
        topPanel.add(createProduct);
        content.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(returnHomeButton);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(logOutButton);
        content.add(bottomPanel, BorderLayout.SOUTH);

        // setLayout(new GridLayout(8, 2));

        // Adding components to the frame
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(2, 5, 2, 5);

        middlePanel.add(productNameLabel, gbc);
        gbc.gridx++;
        middlePanel.add(productNameField);
        gbc.gridx--;
        gbc.gridy++;
        middlePanel.add(productDescLabel, gbc);
        gbc.gridx++;
        middlePanel.add(productDescField, gbc);
        gbc.gridx--;
        gbc.gridy++;
        middlePanel.add(productPriceLabel, gbc);
        gbc.gridx++;
        middlePanel.add(productPriceField, gbc);
        gbc.gridx--;
        gbc.gridy++;
        middlePanel.add(productStockLabel, gbc);
        gbc.gridx++;
        middlePanel.add(productStockField, gbc);
        gbc.gridx--;
        gbc.gridy++;
        middlePanel.add(productIDLabel, gbc);
        gbc.gridx++;
        middlePanel.add(productIDField, gbc);
        gbc.gridx--;
        gbc.gridy++;
        middlePanel.add(storeIDLabel, gbc);
        gbc.gridx++;
        middlePanel.add(storeIDField, gbc);
        gbc.gridx--;
        gbc.gridy++;
        middlePanel.add(sellerIDLabel, gbc);
        gbc.gridx++;
        middlePanel.add(sellerIDField, gbc);
        gbc.gridy++;
        middlePanel.add(createProductButton, gbc);

        content.add(middlePanel, BorderLayout.CENTER);
    }

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(new SellerCreateProductGUI());
    // }
}