package bullscows;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static int turn = 1;
    private static String generateSecretCode(int length) {
        StringBuilder strBuilder = new StringBuilder();
        if (length > 10) {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough " +
                    "unique digits.");
            return null;
        } else {
            boolean isFirst = true;
            long pseudoRandomNumber = System.nanoTime();
            StringBuilder reverseStr = new StringBuilder(String.valueOf(pseudoRandomNumber));
            String randomNumber = new String(reverseStr.reverse().toString());
            List<Character> randomNumberList = new ArrayList();

            for (int i = 0; randomNumberList.size() < length; i++) {
                if (isFirst) {
                    if (randomNumber.charAt(i) != '0') {
                        randomNumberList.add(randomNumber.charAt(i));
                        isFirst = false;
                    }
                } else {
                    if (!randomNumberList.contains(randomNumber.charAt(i))) {
                        randomNumberList.add(randomNumber.charAt(i));
                    }
                }
            }


            for (Character ch : randomNumberList) {
                strBuilder.append(ch);
            }
            //System.out.println("The random secret number is " + strBuilder.toString());
        }
        return strBuilder.toString();
    }

    private static void guessSecretNumber(String secretCode, int length) {

        int index = 0;
        int bulls = 0;
        int cows = 0;

        System.out.println("Turn " + turn + ":");
        String input = scanner.next();

        while (index < length) {
            if (secretCode.charAt(index) == input.charAt(index)) {
                ++bulls;
            } else {
                if (secretCode.contains(String.valueOf(input.charAt(index)))) {
                    ++cows;
                }
            }
            ++index;
        }

        System.out.print("Grade: ");
//        if (bulls == 4 && cows == 0) {
//            System.out.print(bulls + " bull(s).");
//        } else
        if (bulls > 0 && cows > 0) {
            if (bulls == 1) {
                System.out.print(bulls + " bull and ");
            } else {
                System.out.print(bulls + " bulls and ");
            }

            if (cows == 1) {
                System.out.print(cows + " cow");
            } else {
                System.out.print(cows + " cows");
            }
            System.out.println();
            ++turn;
            guessSecretNumber(secretCode, length);
        } else if (bulls > 0 && cows == 0 && secretCode.equals(input)) {
            System.out.print(bulls + " bulls");
            System.out.println();
            System.out.println("Congratulations! You guessed the secret code.");
        } else if (bulls > 0 && cows == 0 && !secretCode.equals(input)) {
            System.out.print(bulls + " bulls");
            System.out.println();
            ++turn;
            guessSecretNumber(secretCode, length);
        } else if (bulls == 0 && cows > 0) {
            if (cows == 1) {
                System.out.print(cows + " cow");
            } else {
                System.out.print(cows + " cows");
            }
            System.out.println();
            ++turn;
            guessSecretNumber(secretCode, length);
        } else if (bulls == 0 && cows == 0) {
            System.out.println("Cannot find number of bulls or number of cows or None after the input");
            ++turn;
            guessSecretNumber(secretCode, length);

        }
    }

    public static void main(String[] args) {
        System.out.println("Please, enter the secret code's length:");
        int length = scanner.nextInt();
        String secretNumber = generateSecretCode(length);
        System.out.println("Okay, let's start a game!");
        guessSecretNumber(secretNumber, length);
        scanner.close();
    }
}
