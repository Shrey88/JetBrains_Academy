import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int input;
        int sum = 0;

        while ((input = scanner.nextInt()) != 0 && sum < 1000) {
            sum += input;
        }
        if (sum < 1000) {
            System.out.println(sum);
        } else if (sum >= 1000) {
            System.out.println(sum - 1000);
        }
    }
}