import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int num = scanner.nextInt();

        int first = num / 100;
        num = num % 100;
        int second = num / 10;
        num = num % 10;

        System.out.println(first + second + num);

    }
}