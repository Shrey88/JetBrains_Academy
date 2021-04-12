import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int length = 0;
        String str = scanner.nextLine();
        String[] strArr = str.split(" ");
        String longestWord = null;
        for (String value : strArr) {
            if (length < value.length()) {
                length = value.length();
                longestWord = value;
            }
        }
        System.out.println(longestWord);
    }
}