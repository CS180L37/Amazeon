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
}
