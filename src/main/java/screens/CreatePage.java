package screens;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatePage extends JComponent implements Runnable{
    // Text fields
    JTextField productNameField, productDescField, productPriceField, productStockField, productIDField, storeIDField;
    JButton logoutButton;
    JButton homeButton;


    public CreatePage() {

    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == logoutButton) {
                JOptionPane.showMessageDialog(null, "Logged Out!");
            }
            if(e.getSource() == homeButton) {
                JOptionPane.showMessageDialog(null, "Returning Home!");

            }
        }
    };


    public void run() {
        // Setting up the frame
        JFrame frame = new JFrame();

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setTitle("Product Page");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Creating labels
        JLabel productNameLabel = new JLabel("Product Name:");
        JLabel productDescLabel = new JLabel("Product Description:");
        JLabel productPriceLabel = new JLabel("Product Price:");
        JLabel productStockLabel = new JLabel("Product Stock:");
        JLabel productIDLabel = new JLabel("Product ID:");
        JLabel storeIDLabel = new JLabel("Store ID:");

        // Creating text fields
        productNameField = new JTextField(10);
        productDescField = new JTextField(10);
        productPriceField = new JTextField(10);
        productStockField = new JTextField(10);
        productIDField = new JTextField(10);
        storeIDField = new JTextField(10);

        // Creating buttons
        logoutButton = new JButton("Log Out");
        homeButton = new JButton("Return Home");

        // Setting layout
        setLayout(new GridLayout(8, 2));

        // Adding components to the frame
        add(productNameLabel);
        add(productNameField);
        add(productDescLabel);
        add(productDescField);
        add(productPriceLabel);
        add(productPriceField);
        add(productStockLabel);
        add(productStockField);
        add(productIDLabel);
        add(productIDField);
        add(storeIDLabel);
        add(storeIDField);
        add(logoutButton);
        add(homeButton);



    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new CreatePage());
    }
}