package bullscows;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static int turn = 1;

    private static boolean isAlphabetic(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static String generateSecretCode(int length, int possibleCharacters) {
        StringBuilder strBuilder = new StringBuilder();
        if (length > 36) {
            System.out.println("Error: can't generate a secret number with a length of 37 because there aren't enough " +
                    "unique digits.");
            return null;
        } else {
            Random alphaRandom = new Random(97);
            Random numericRandom = new Random(0);
            Random chooseChar = new Random(0);
            List<Character> secretCode = new ArrayList();
            Character randomNumValue = null;

            for (int i = 0; secretCode.size() < length; i++) {
                ;
                if (chooseChar.nextInt(2) == 1) {
                    
                    if (possibleCharacters < 10) {
                        randomNumValue = String.valueOf(numericRandom.nextInt(possibleCharacters)).charAt(0);
                    } else {
                        randomNumValue = String.valueOf(numericRandom.nextInt(10)).charAt(0);
                    }
                } else {
                    
                    if (possibleCharacters > 10 && possibleCharacters - 10 == 1) {
                        randomNumValue = (char)97;
                    } else if (possibleCharacters > 10 && possibleCharacters - 10 > 1){
                        int endLimit = 97 + (possibleCharacters - 10);
                        randomNumValue = (char) Integer.parseInt(String.valueOf(alphaRandom.nextInt(endLimit - 97) + 97));
                    }
                }

                if (!secretCode.contains(randomNumValue)) {
                    secretCode.add(randomNumValue);
                }
            }

            System.out.print("The secret is prepared: ");
            for (Character ch : secretCode) {
                strBuilder.append(ch);
                System.out.print("*");
            }

            if (possibleCharacters < 10) {
                System.out.print(" (0-" + possibleCharacters + ").\n");
            } else if (possibleCharacters == 10) {
                System.out.print(" (0-" + (possibleCharacters - 1) + ").\n");
            } else if (possibleCharacters > 10) {
                System.out.print(" (0-9, a-" +
                        ((char)(97 + (possibleCharacters - 10) - 1)) + ").\n");
            }

        }
        return strBuilder.toString();
    }

    private static void guessSecretNumber(String secretCode, int length) {

        int index = 0;
        int bulls = 0;
        int cows = 0;


        System.out.println("Turn " + turn + ":");
        String input = scanner.next();


        if (isAlphabetic(input)) {
            System.out.println("\"" + input + "\" isn\'t a valid number.");
        }
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
        System.out.println("Input the length of the secret code:");
        String length = scanner.nextLine();
        if (isAlphabetic(length) || Integer.parseInt(length) <= 0) {
            System.out.println("Error: \"" + length + "\" isn\'t a valid number.");
            return;
        }

        System.out.println("Input the number of possible symbols in the code:");
        int possibleCharacters = scanner.nextInt();

        if (Integer.parseInt(length) > possibleCharacters) {
            System.out.println("Error: it's not possible to generate a code with a length of " +
                    length + " with " + possibleCharacters + " unique symbols.");
        } else if (possibleCharacters > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
        } else {
            String secretNumber = generateSecretCode(Integer.parseInt(length), possibleCharacters);
            System.out.println("Okay, let's start a game!");
            guessSecretNumber(secretNumber, Integer.parseInt(length));
            scanner.close();
        }
    }
}
