import java.util.Scanner;

public class Main {

    public static boolean isVowel(char ch) {
        // write your code here
        boolean isVowel = false;

        if (ch == 'a' || ch == 'A') {
            isVowel = true;
        } else if (ch == 'e' || ch == 'E') {
            isVowel = true;
        } else if (ch == 'i' || ch == 'I') {
            isVowel = true;
        } else if (ch == 'o' || ch == 'O') {
            isVowel = true;
        } else if (ch == 'u' || ch == 'U') {
            isVowel = true;
        }

        return isVowel;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char letter = scanner.nextLine().charAt(0);
        System.out.println(isVowel(letter) ? "YES" : "NO");
    }
}