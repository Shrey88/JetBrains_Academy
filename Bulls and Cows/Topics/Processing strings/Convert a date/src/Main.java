import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String inputDate = scanner.nextLine();

        // Parses the date
        LocalDate dt = LocalDate.parse(inputDate);

        // Date Formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
        System.out.println(formatter.format(dt));
    }
}