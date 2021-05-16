package correcter;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static final char[] characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ".toCharArray();
    private static final Scanner scanner = new Scanner(System.in);

    private static String symbolTriplet(String inputStr) {
        StringBuilder strBuilder = new StringBuilder();

        for(int index = 0; index < inputStr.length(); index++) {
            strBuilder.append(String.valueOf(inputStr.charAt(index)).repeat(3));
        }

        return strBuilder.toString();
    }

    private static String encodedString(StringBuilder inputStr) {
        int charIndex;
        int inputIndex;

        for (int i = 3 ; i <= inputStr.length(); i += 3) {
            charIndex = ThreadLocalRandom.current().nextInt(characters.length - 1);
            inputIndex = ThreadLocalRandom.current().nextInt(2);
            inputStr.setCharAt(i - 1 - inputIndex , characters[charIndex]);
        }

        return inputStr.toString();
    }

    private static String decodedString(StringBuilder inputStr) {
        StringBuilder decodedString = new StringBuilder();

        for (int i = 0; i < inputStr.length(); i += 3) {
            if (inputStr.charAt(i) == inputStr.charAt(i + 1) || inputStr.charAt(i) == inputStr.charAt(i + 2)) {
                decodedString.append(inputStr.charAt(i));
            } else if (inputStr.charAt(i + 1) == inputStr.charAt(i + 2) ) {
                decodedString.append(inputStr.charAt(i + 1));
            }
        }

        return decodedString.toString();
    }

    public static void main(String[] args) {

        StringBuilder input = new StringBuilder(scanner.nextLine());
        System.out.println(input.toString());

        String modifiedString = symbolTriplet(input.toString());
        input = new StringBuilder(modifiedString);
        System.out.println(input.toString());

        modifiedString = encodedString(input);
        input = new StringBuilder(modifiedString);
        System.out.println(input.toString());

        modifiedString = decodedString(input);
        System.out.println(modifiedString);
    }
}
