package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import com.google.cloud.firestore.Query;
import models.Cart;
import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import models.Store;
import utils.fields;

public class SellerUpdateProductGUI extends JComponent implements Runnable {
    JFrame frame;
    JButton editProductButton;
    JButton returnHomeButton;
    JButton logOutButton;
    JTextField strId, nme, desc, quan, prce;


    Seller seller;
    Product product;

    public SellerUpdateProductGUI(Seller seller, Product product) {
        this.seller = seller;
        this.product = product;
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == editProductButton) {
                for (int i = 0; i < seller.getProducts().size(); i++) {
                    if (seller.getProducts().get(i).equals(product)) {
                        if (!strId.getText().equals("")) {
                            seller.getProducts().get(i).setStoreId(Integer.parseInt(strId.getText()));
                        } else if(strId.getText().equals("")) {
                            strId.getText();
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Store ID Input!", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        if (!nme.getText().equals("")) {
                            seller.getProducts().get(i).setName(nme.getText());
                        } else if(nme.getText().equals("")) {
                            nme.getText();
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Name Input!", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        if (!desc.getText().equals("")) {
                            seller.getProducts().get(i).setDescription(desc.getText());
                        } else if(desc.getText().equals("")) {
                            desc.getText();
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Description Input!", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        if (!quan.getText().equals("")) {
                            seller.getProducts().get(i).setQuantity(Integer.parseInt(quan.getText()));
                        } else if(quan.getText().equals("")) {
                            quan.getText();
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Quantity Input!", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        if (!prce.getText().equals("")) {
                            seller.getProducts().get(i).setPrice(Double.parseDouble(prce.getText()));
                        } else if(prce.getText().equals("")) {
                            prce.getText();
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Price Input!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                frame.dispose();
                JOptionPane.showMessageDialog(null, "Product Updated!", "Update Product",
                        JOptionPane.INFORMATION_MESSAGE);
                try {
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
        frame = new JFrame();
        try {
            frame.setIconImage(javax.imageio.ImageIO.read(new java.io.File("src/main/resources/logo.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setTitle("Update Product Page");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JLabel productName = new JLabel("Product ID: " + product.getName());
        JLabel newStoreID = new JLabel("Enter new Store Id: ");
        JLabel newName = new JLabel("Enter new name: ");
        JLabel newDescription = new JLabel("Enter new description: ");
        JLabel newQuantity = new JLabel("Enter new quantity: ");
        JLabel newPrice = new JLabel("Enter new price: ");


        strId = new JTextField(10);
        strId.setText(String.valueOf(product.getStoreId()));
        strId.setEnabled(false);
        nme = new JTextField(10);
        desc = new JTextField(10);
        quan = new JTextField(10);
        prce = new JTextField(10);

        editProductButton = new JButton("Edit Product");
        editProductButton.addActionListener(actionListener);
        returnHomeButton = new JButton("Return Home");
        returnHomeButton.addActionListener(actionListener);
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(actionListener);

        JPanel topPanel = new JPanel();
        topPanel.add(productName);
        content.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(returnHomeButton);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(logOutButton);
        content.add(bottomPanel, BorderLayout.SOUTH);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        middlePanel.add(newStoreID, gbc);
        gbc.gridx++;
        middlePanel.add(strId, gbc);
        gbc.gridx--;
        gbc.gridy++;
        middlePanel.add(newName, gbc);
        gbc.gridx++;
        middlePanel.add(nme, gbc);
        gbc.gridx--;
        gbc.gridy++;
        middlePanel.add(newDescription, gbc);
        gbc.gridx++;
        middlePanel.add(desc, gbc);
        gbc.gridx--;
        gbc.gridy++;
        middlePanel.add(newQuantity, gbc);
        gbc.gridx++;
        middlePanel.add(quan, gbc);
        gbc.gridx--;
        gbc.gridy++;
        middlePanel.add(newPrice, gbc);
        gbc.gridx++;
        middlePanel.add(prce, gbc);
        // gbc.gridx--;
        gbc.gridy++;
        middlePanel.add(editProductButton, gbc);

        content.add(middlePanel, BorderLayout.CENTER);
    }
    // public static void main(String[] args) {
    // Product product = new Product(1, "Sour Patch", 7, "sweet and sour candy",
    // 3.00, 1, 1);
    // SwingUtilities.invokeLater(new EditProductGUI(product));
    // }
}
