//package screens;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//import models.Cart;
//import models.Customer;
//import models.Product;
//import models.Sale;
//import models.Seller;
//import models.Store;
//
//public class SellerCartGUI extends JComponent implements Runnable {
//    JFrame frame;
//    JButton returnHomeButton;
//    JButton logOutButton;
//
//    ActionListener actionListener = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if(e.getSource() == logOutButton) {
//                frame.dispose();
//                SwingUtilities.invokeLater(new LoginGUI());
//            }
//            if(e.getSource() == returnHomeButton) {
//                try {
//                    frame.dispose();
//                    SwingUtilities.invokeLater(new SellerMarketplaceGUI());
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//
//            }
//        }
//    };
//    public void run() {
//        frame = new JFrame();
//
//        Container content = frame.getContentPane();
//        content.setLayout(new BorderLayout());
//
//        frame.setSize(600, 400);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setVisible(true);
//
//        JPanel topPanel = new JPanel();
//        topPanel.add(new JLabel("Cart"));
//        content.add(topPanel, BorderLayout.NORTH);
//
//        returnHomeButton = new JButton("Return Home");
//        returnHomeButton.addActionListener(actionListener);
//        logOutButton = new JButton("Log Out");
//        logOutButton.addActionListener(actionListener);
//
//        JPanel bottomPanel = new JPanel();
//        bottomPanel.add(returnHomeButton);
//        bottomPanel.add(new JLabel("     "));
//        bottomPanel.add(logOutButton);
//        content.add(bottomPanel, BorderLayout.SOUTH);
//
////        JPanel middlePanel = new JPanel();
////        middlePanel.setLayout(new GridLayout(0,1));
////        for(int i = 0; i < sales.size(); i++){
////            ArrayList<Product> cartProducts = getCustomerById(sales.get(i).getCustomerId()).getCart().getCartProducts();
////            for(int j = 0; j < cartProducts.size(); j++) {
////                JLabel label = new JLabel("<html>" +
////                        "<div style='text-align: center;'>" +
////                        "<div>" + "Customer Name: " + getCustomerbyId(sales.get(i).getCustomerId()).getName() + "</div>" +
////                        "<div>" + "Store Name: " + getStoreById(cartProducts.get(j).getStoreId()).getName() + "</div>" +
////                        "<div>" + "Product Description: $" + cartProducts.get(j).getDescription() + "0" + "</div>" +
////                        "<div>" + "Product Price: $" + cartProducts.get(j).getPrice() + "0" + "</div>" +
////                        "</div>" +
////                        "</html>");
////
////                middlePanel.add(label);
////            }
////        }
////        content.add(middlePanel, BorderLayout.CENTER);
//    }
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new SellerCartGUI());
//    }
//}
