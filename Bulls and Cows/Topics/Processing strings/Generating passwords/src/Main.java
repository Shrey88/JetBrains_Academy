import java.util.*;

public class Main {
    private static String str = "";

    private static void getUpperCaseLetters(int upperCase, int totalSymbols) {
        char letter = 'A';
        int i = 1;

        /*
         *  iterate half of the given no
         */
        while (str.length() <= totalSymbols && i <= totalSymbols &&
                upperCase > 0 && i <= Math.round((double) upperCase / 2)) {
            str += letter;

            /*
             *  if the value of the variable letter reaches 'Z' increment i and break the loop
             */
            if (letter == 'Z') {
                ++i;
                break;
            }
            ++letter;
            ++i;
        }

        /*
         *  Iterate for the remaining half starting with letter A again.
         *
         *  In Case while iterating it reaches Z reset the letter variable to A
         */
        letter = 'A';
        while (str.length() < totalSymbols && i <= totalSymbols &&
                upperCase > 0 && i <= upperCase) {
            if (str.charAt(str.length() - 1) == letter) {
                /*
                 *  reset the value of the variable letter to 'A' if it reaches 'Z'
                 */
                if (letter == 'Z') {
                    letter = 'A';
                } else {
                    ++letter;
                }
            }

            str += letter;

            /*
             *  reset the value of the variable letter to 'A' if it reaches 'Z'
             */
            if (letter == 'Z') {
                letter = 'A';
            } else {
                ++letter;
            }
            ++i;
        }
    }

    private static void getLowerCaseLetters(int lowerCase, int totalSymbols) {
        char letter = 'a';
        int i = 1;

        /*
         *  iterate for the given no
         */
        if (str.length() != totalSymbols) {
            while (i <= totalSymbols && lowerCase > 0 && i <= lowerCase) {
                str += letter;
                /*
                 *  if the letter reaches the character z then reset it to 'a' and continue.
                 */
                if (letter == 'z') {
                    letter = 'a';
                } else {
                    ++letter;
                }
                ++i;
            }
        }
    }

    private static void getNumbers(int numbers, int totalSymbols) {
        int digit = 1;
        int i = 1;

        if (str.length() != totalSymbols) {
            while (i <= totalSymbols && numbers > 0 && i <= Math.round((double) numbers / 2)) {
                str += digit;
                if (digit == 9) {
                    ++i;
                    break;
                }
                ++digit;
                ++i;
            }
        }

        /*
         *  reset the character to A
         */
        digit = 1;
        if (str.length() != totalSymbols) {
            while (i <= totalSymbols && numbers > 0 && i <= numbers) {
                str += digit;
                if (digit == 9) {
                    digit = 1;
                } else {
                    ++digit;
                }
                ++i;
            }
        }
    }

    private static void getDiffSymbols(int totalSymbols, int diffSymbols) {
        char letter = 'A';
        int i = 1;

        if (str.length() != totalSymbols) {
            while (i <= totalSymbols && diffSymbols > 0 && i <= diffSymbols) {
                /*
                 * Check what is the last character, if it's 'A' increment the character and add to the password.
                 */
                if (str.length() > 0 && str.charAt(str.length() - 1) == letter) {
                    ++letter;
                }
                str += letter;

                /*
                 *  if the value of the variable letter reaches 'Z' break the loop and increment i
                 */
                if (letter == 'Z') {
                    letter = 'A';
                } else {
                    ++letter;
                }
                ++i;
            }
        }
    }

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        int upperCase = scanner.nextInt();
        int lowerCase = scanner.nextInt();
        int numbers = scanner.nextInt();
        int totalSymbols = scanner.nextInt();
        getUpperCaseLetters(upperCase, totalSymbols);
        getLowerCaseLetters(lowerCase, totalSymbols);
        getNumbers(numbers, totalSymbols);
        getDiffSymbols(totalSymbols, totalSymbols - (upperCase + lowerCase + numbers));

        System.out.println(str);

    }
}