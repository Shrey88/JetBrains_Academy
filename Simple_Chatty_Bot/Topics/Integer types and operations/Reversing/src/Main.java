import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int num = scanner.nextInt();

        int first = num % 10;
        num /= 10;
        int second = num % 10;
        num /= 10;
        int third = num % 10;

        if (first != 0) {
            System.out.print(first);
        }
        System.out.print(second);
        System.out.print(third);
    }
}