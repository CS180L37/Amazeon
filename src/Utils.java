import java.io.BufferedReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static final int YES = 1;
    public static final int NO = 0;
    public static final int ERROR = -1;

    public static int validInput(String input) {
        input = input.toLowerCase();
        if (input.equals("y") || input.equals("yes")) {
            return YES;
        } else if (input.equals("n") || input.equals("no")) {
            return NO;
        }
        return ERROR;
    }

    // Check if the email is valid
    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9_.]*@[A-Za-z0-9_.].[A-Za-z0-9]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static BufferedReader createReader(String filename) {

    }
}
