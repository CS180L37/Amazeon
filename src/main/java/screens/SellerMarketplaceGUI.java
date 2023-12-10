package screens;

import javax.swing.*;

import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;

import static utils.Utils.DOWNLOADS;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import models.Cart;
import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import models.Store;

public class SellerMarketplaceGUI extends JComponent implements Runnable {
    JFrame frame;
    JButton createButton;
    JButton editButton;
    JButton deleteButton;
    JButton salesButton;
    JButton dashboardButton;
    JButton cartButton;
    JButton dataButton;
    JButton logOutButton;

    JPopupMenu sortDashboardMenu;
    JMenuItem menuItemSort1;
    JMenuItem menuItemSort2;
    JPopupMenu dataMenu;
    JMenuItem menuItemExportData;
    JMenuItem menuItemImportData;

    JFileChooser fileChooser;

    Seller seller;

    // constructor -- needed to create this mock data
    public SellerMarketplaceGUI(Seller seller) throws IOException {
        this.seller = seller;
    }

    // action listeners --> depending on what button you click, you do certain
    // things (for now only the dropdowns are implemented)
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == createButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new SellerCreateProductGUI(seller));
            }
            if (e.getSource() == deleteButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new SellerDeleteProductGUI(seller));
            }
            if (e.getSource() == editButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new SellerEditProductGUI(seller));
            }
            if (e.getSource() == dashboardButton) {
                sortDashboardMenu.show(dashboardButton, 0, dashboardButton.getHeight());
            }
            if (e.getSource() == menuItemSort1) {
                frame.dispose();
                SwingUtilities.invokeLater(new SellerDashboardOneGUI(seller));
            }
            if (e.getSource() == menuItemSort2) {
                frame.dispose();
                SwingUtilities.invokeLater(new SellerDashboardTwoGUI(seller));
            }
            if (e.getSource() == dataButton) {
                dataMenu.show(dataButton, 0, dataButton.getHeight());
            }
            if (e.getSource() == menuItemExportData) {
                if (seller.exportProducts()) {
                    JOptionPane.showMessageDialog(null,
                            "Export to " + DOWNLOADS + "products.csv" + " was successful!", "Export successful",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Export to " + DOWNLOADS + "products.csv" + " was unsuccessful!", "Export unsuccessful",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == menuItemImportData) {
                fileChooser = new JFileChooser(DOWNLOADS);
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    if (!filePath.contains(".csv")) {
                        JOptionPane.showMessageDialog(null, "Invalid file type", "File Selection",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    ArrayList<Product> newProducts = seller.importProducts(filePath);
                    if (!newProducts.isEmpty()) {
                        seller.setProducts(newProducts);
                        JOptionPane.showMessageDialog(null, "Import from " + filePath + " was successful!",
                                "Import successful",
                                JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        run();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Import from " + filePath + "was unsuccessful!", "Import unsuccessful",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            if (e.getSource() == salesButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new SellerSalesGUI(seller));
            }
            if (e.getSource() == cartButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new SellerCartGUI(seller));
            }
            if (e.getSource() == logOutButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new LoginGUI());
            }
        }
    };

    // everything that needs to be displayed goes into the run method
    public void run() {
        frame = new JFrame("Seller Marketplace"); // creates frame
        try {
            frame.setIconImage(javax.imageio.ImageIO.read(new java.io.File("src/main/resources/logo.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Container content = frame.getContentPane(); // creates a container in which things go for that frame
        content.setLayout(new BorderLayout()); // sets layout nicely

        // sets frame style and displays frame
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        // initialize your buttons and add action listsners to each
        createButton = new JButton("Create");
        createButton.addActionListener(actionListener);

        editButton = new JButton("Edit");
        editButton.addActionListener(actionListener);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(actionListener);

        salesButton = new JButton("Sales");
        salesButton.addActionListener(actionListener);

        dashboardButton = new JButton("Dashboard");
        dashboardButton.addActionListener(actionListener);

        dataButton = new JButton("Data");
        dataButton.addActionListener(actionListener);

        cartButton = new JButton("Cart");
        cartButton.addActionListener(actionListener);

        // dashboard button's dropdown menu
        sortDashboardMenu = new JPopupMenu("Dashboard");

        menuItemSort1 = new JMenuItem("Sort Customers By Number of Items Purchased");
        menuItemSort1.addActionListener(actionListener);
        sortDashboardMenu.add(menuItemSort1);

        menuItemSort2 = new JMenuItem("Sort Products By Number of Products Sold");
        menuItemSort2.addActionListener(actionListener);
        sortDashboardMenu.add(menuItemSort2);

        // data dropdown menu
        dataMenu = new JPopupMenu("Data");

        menuItemExportData = new JMenuItem("Export Product Data");
        menuItemExportData.addActionListener(actionListener);
        dataMenu.add(menuItemExportData);

        menuItemImportData = new JMenuItem("Import Product Data");
        menuItemImportData.addActionListener(actionListener);
        dataMenu.add(menuItemImportData);

        // creates panel at top of frame and adds buttons
        JPanel topPanel = new JPanel();
        topPanel.add(createButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);
        topPanel.add(salesButton);
        topPanel.add(dashboardButton);
        topPanel.add(cartButton);
        topPanel.add(dataButton);

        content.add(topPanel, BorderLayout.NORTH); // adds the panel to the container

        // creates panel at bottom and adds log out button
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(actionListener);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(logOutButton);

        content.add(bottomPanel, BorderLayout.SOUTH); // adds panel to container

        // creates panel in middle
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(0, 1)); // creates layout needed for a vertical arranagement of products in
                                                     // the marketplace

        // iterates through stores list and each stores products' list in order to
        // display product information
        // change to seller's products (currently it is customer's products)
        for (int i = 0; i < seller.getProducts().size(); i++) {
            JButton productButton;
            try {
                productButton = new JButton( // html used for style purposes only
                        "<html>" +
                                "<div style='text-align: center;'>" +
                                "<div>" + "Product Name: " + seller.getProducts().get(i).getName() + "</div>" +
                                "<div>" + "StoreName: "
                                + Store.getStoreById(seller.getProducts().get(i).getStoreId()).getName()
                                + "</div>" +
                                "<div>" + "Product Stock: " + seller.getProducts().get(i).getQuantity() + "</div>" +
                                "</div>" +
                                "</html>");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            productButton.setPreferredSize(new Dimension(200, 100)); // sets size of each product button
            middlePanel.add(productButton);

        }

        // sets up a scroll bar for panel
        JScrollPane scrollPane = new JScrollPane(middlePanel);

        // Set preferred size of the scroll pane
        scrollPane.setPreferredSize(new Dimension(200, 300));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        content.add(scrollPane, BorderLayout.CENTER); // adds the scroll bar to the container

    }

    // public static void main (String[] args) { //runs the program
    // try{
    // SwingUtilities.invokeLater(new SellerMarketplaceGUI());
    // } catch(Exception e){
    // e.printStackTrace();
    // }
    // }
}
