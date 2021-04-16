import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws Exception {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int count = 0;

            String inputStr = reader.readLine();
            inputStr = inputStr.trim();
            if (!inputStr.isEmpty()) {
                Scanner scanner = new Scanner(inputStr);
                String word = scanner.next();
                while (!" ".equals(word) && !"\t".equals(word)) {
                    ++count;
                    if (scanner.hasNext()) {
                        word = scanner.next();
                    } else {
                        break;
                    }
                }
            }

            System.out.println(count);
        } catch (Exception e) {
            System.out.println("An exception occurred " + e.getMessage());
        }

    }
}