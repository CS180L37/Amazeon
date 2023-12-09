package screens;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import models.Cart;
import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import models.Store;

import utils.fields;

public class CreateAccountGUI extends JComponent implements Runnable {
    JFrame frame;
    JTextField emailTextField; // email input text field
    JTextField passwordTextField; // password input text field
    // asks whether they will login as customer or seller
    JToggleButton customerButton;
    JToggleButton sellerButton;
    JButton loginButton; // login button
    JButton createAccountButton; // should display createAccountGUI
    String email;
    String password;
    String name;
    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == createAccountButton) {
                email = String.valueOf(emailTextField.getText());
                password = String.valueOf(passwordTextField.getText());
                if (customerButton.isSelected()) {
                    try {
                        Customer.createCustomer(email, password);
                        Customer customer = Customer.getCustomerByEmail(email);
                        frame.dispose();
                        SwingUtilities.invokeLater(new CustomerMarketplaceGUI(customer));
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Email/Password. Please try again!", "Error Message", JOptionPane.ERROR_MESSAGE);
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        Seller.createSeller(email, password, name);
                        Seller seller = Seller.getSellerByEmail(email);
                        frame.dispose();
                        SwingUtilities.invokeLater(new SellerMarketplaceGUI(seller));
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Email/Password. Please try again!", "Error Message", JOptionPane.ERROR_MESSAGE);
                        throw new RuntimeException(ex);
                    }
                }
            }
            if (e.getSource() == loginButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new LoginGUI());
            }
            if (e.getSource() == customerButton) {
                sellerButton.setSelected(false);
            }
            if (e.getSource() == sellerButton) {
                customerButton.setSelected(false);
                name = JOptionPane.showInputDialog("Enter Your Name: ");
            }
        }
    };

    FocusListener focusListener = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            if (e.getSource() == emailTextField) {
                if (emailTextField.getText().equals("Enter email you would like to use")) {
                    emailTextField.setText("");
                }
            }
            if (e.getSource() == passwordTextField) {
                if (passwordTextField.getText().equals("Enter a password")) {
                    passwordTextField.setText("");
                }
            }

        }

        @Override
        public void focusLost(FocusEvent e) {
            if (e.getSource() == emailTextField) {
                if (emailTextField.getText().isEmpty()) {
                    emailTextField.setText("Enter email you would like to use");
                }
            }
            if (e.getSource() == passwordTextField) {
                if (passwordTextField.getText().isEmpty()) {
                    passwordTextField.setText("Enter a password");
                }
            }
        }
    };

    public void run() {
        frame = new JFrame("Create Account");
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

        emailTextField = new JTextField("Enter email you would like to use", 25);
        emailTextField.addFocusListener(focusListener);
        passwordTextField = new JTextField("Enter a password", 25);
        passwordTextField.addFocusListener(focusListener);
        customerButton = new JToggleButton("Customer");
        customerButton.addActionListener(actionListener);
        sellerButton = new JToggleButton("Seller");
        sellerButton.addActionListener(actionListener);
        loginButton = new JButton("Login");
        loginButton.addActionListener(actionListener);
        createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(actionListener);

        JPanel topPanel = new JPanel();
        JLabel topTextLabel = new JLabel("CREATE ACCOUNT");
        topPanel.add(topTextLabel);

        content.add(topPanel, BorderLayout.NORTH);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(7, 7, 7, 7);

        middlePanel.add(emailTextField, gbc);

        gbc.gridy++;
        middlePanel.add(passwordTextField, gbc);

        JPanel userTypePanel = new JPanel();
        userTypePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        userTypePanel.add(customerButton);
        userTypePanel.add(sellerButton);

        gbc.gridy++;
        gbc.gridx = 0;
        middlePanel.add(userTypePanel, gbc);

        gbc.gridy++;
        createAccountButton.setPreferredSize(new Dimension(130, 25));
        createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        middlePanel.add(createAccountButton, gbc);

        content.add(middlePanel);

        JPanel bottomPanel = new JPanel();
        JLabel bottomTextLabel = new JLabel("Already have an account?");
        bottomPanel.add(bottomTextLabel);
        loginButton.setPreferredSize(new Dimension(70, 20));
        bottomPanel.add(loginButton);

        content.add(bottomPanel, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new CreateAccountGUI());
    }
}
