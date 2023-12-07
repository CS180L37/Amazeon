package screens;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class CreateAccountGUI extends JComponent implements Runnable{
    JFrame frame;
    JTextField emailTextField; //email input text field
    JTextField passwordTextField; //password input text field
    JButton loginButton; //login button
    JButton createAccountButton; //should display createAccountGUI
    String email;
    String password;
    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == createAccountButton) {
                email = String.valueOf(emailTextField.getText());
                password = String.valueOf(passwordTextField.getText());
                System.out.println(email + " " + password);
                try {
                    frame.dispose();
                    SwingUtilities.invokeLater(new CustomerMarketplaceGUI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if(e.getSource() == loginButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new LoginGUI());
            }
        }
    };

    FocusListener focusListener = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            if(e.getSource() == emailTextField){
                if(emailTextField.getText().equals("Enter email you would like to use")){
                    emailTextField.setText("");
                }
            }
            if(e.getSource() == passwordTextField){
                if(passwordTextField.getText().equals("Enter a password")){
                    passwordTextField.setText("");
                }
            }

        }

        @Override
        public void focusLost(FocusEvent e) {
            if(e.getSource() == emailTextField){
                if(emailTextField.getText().isEmpty()) {
                    emailTextField.setText("Enter email you would like to use");
                }
            }
            if(e.getSource() == passwordTextField){
                if(passwordTextField.getText().isEmpty()) {
                    passwordTextField.setText("Enter a password");
                }
            }
        }
    };

    public void run() {
        frame = new JFrame("Create Account");

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

        gbc.gridy++;
        createAccountButton.setPreferredSize(new Dimension(130, 20));
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