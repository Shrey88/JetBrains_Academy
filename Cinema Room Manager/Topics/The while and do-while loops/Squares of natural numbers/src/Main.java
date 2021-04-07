import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int digit = scanner.nextInt();

        int i = 1;
        do {
            System.out.println((int) Math.pow(i, 2));
            ++i;
        }
        while (Math.pow(i, 2) <= digit);
    }
}