package screens;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import models.Cart;
import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import models.Store;

public class LoginGUI extends JComponent implements Runnable{
    JFrame frame;
    JTextField emailTextField; //email input text field
    JTextField passwordTextField; //password input text field
    JButton loginButton; //login button
    JButton createAccountButton; //should display createAccountGUI

    String email;
    String password;

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == loginButton) {
                email = String.valueOf(emailTextField.getText());
                password = String.valueOf(passwordTextField.getText());
                System.out.println(email + " " + password);
                try {
                    if(Customer.customerExists(email, password)) {
                        Customer customer = Customer.getCustomerByEmail(email);
                        frame.dispose();
                        SwingUtilities.invokeLater((new CustomerMarketplaceGUI(customer)));
                    }
                    else {
                        if(Seller.sellerExists(email, password)) {
                            Seller seller = Seller.getSellerByEmail(email);
                            frame.dispose();
                            SwingUtilities.invokeLater(new SellerMarketplaceGUI(seller));
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid username or password! Please try again.","Invalid Account", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if(e.getSource() == createAccountButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new CreateAccountGUI());
            }
        }
    };

    FocusListener focusListener = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            if(e.getSource() == emailTextField){
                if(emailTextField.getText().equals("Email")){
                    emailTextField.setText("");
                }
            }
            if(e.getSource() == passwordTextField){
                if(passwordTextField.getText().equals("Password")){
                    passwordTextField.setText("");
                }
            }

        }

        @Override
        public void focusLost(FocusEvent e) {
            if(e.getSource() == emailTextField){
                if(emailTextField.getText().isEmpty()) {
                    emailTextField.setText("Email");
                }
            }
            if(e.getSource() == passwordTextField){
                if(passwordTextField.getText().isEmpty()) {
                    passwordTextField.setText("Password");
                }
            }
        }
    };

    public void run() {
        frame = new JFrame("Login");

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        emailTextField = new JTextField("Email", 25);
        emailTextField.addFocusListener(focusListener);
        passwordTextField = new JTextField("Password", 25);
        passwordTextField.addFocusListener(focusListener);
        loginButton = new JButton("Login");
        loginButton.addActionListener(actionListener);
        createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(actionListener);

        JPanel topPanel = new JPanel();
        JLabel topTextLabel = new JLabel("LOGIN");
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

        gbc.gridy++;
        loginButton.setPreferredSize(new Dimension(125, 20));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        middlePanel.add(loginButton, gbc);

        content.add(middlePanel);

        JPanel bottomPanel = new JPanel();
        JLabel bottomTextLabel = new JLabel("Don't have an account?");
        bottomPanel.add(bottomTextLabel);
        createAccountButton.setPreferredSize(new Dimension(130, 20));
        bottomPanel.add(createAccountButton);

        content.add(bottomPanel, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new LoginGUI());
    }
}
