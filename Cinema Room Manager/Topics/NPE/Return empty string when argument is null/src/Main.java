import java.util.Scanner;
import java.util.Locale;

public class Main {

    /* Fix this method */
    public static String toUpperCase(String str) {
        return str.toUpperCase(Locale.ENGLISH);
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if (line == null) {
            System.out.println("");
        } else {
            line = "none".equalsIgnoreCase(line) ? null : line;
            if (line == null) {
                System.out.println("");
            } else {
                System.out.println(toUpperCase(line));
            }
        }
    }
}