import java.io.IOException;

import screens.LoginGUI;
import utils.Utils;

import javax.swing.*;

/// Our entry point and data manager class
/// NOTE: NOTHING SHOULD IMPORT AMAZEON; It is the entry point and imports

public class Amazeon {
    public static void main(String[] args) {
        try {
            Utils.initializeDatabase();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error", "title", JOptionPane.ERROR_MESSAGE);
            return;
        }
        SwingUtilities.invokeLater(new LoginGUI());
    }
}
