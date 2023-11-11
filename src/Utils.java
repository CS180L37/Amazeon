import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static final int YES = 1;
    public static final int NO = 0;
    public static final int ERROR = -1;
    public static final Scanner SCANNER = new Scanner(System.in);

    public static boolean validateYesOrNo(String input) {
        input = input.toLowerCase();
        if (input.equals("y") || input.equals("yes")) {
            return true;
        } else if (input.equals("n") || input.equals("no")) {
            return true;
        }
        return false;
    }

    public static int yesOrNoToInt(String input) {
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

    public static boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        if (matcher.find()) {
            return false;
        }
        if (password.length() < 7) {
            return false;
        }
        return true;
    }

    public static BufferedReader createReader(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
        return br;
    }

    public static String inputPrompt(String prompt, ValidateInterface validateInterface, String... reprompt) {
        System.out.println(prompt);
        String userInput;
        do {
            userInput = Utils.SCANNER.nextLine();
            if (validateInterface.validate(userInput)) {
                return userInput;
            }
            System.out.println((reprompt != null) ? reprompt : prompt);
        } while (true);
    }
}
