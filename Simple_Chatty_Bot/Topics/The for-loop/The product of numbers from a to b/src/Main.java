import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner scanner = new Scanner(System.in);

        long a = scanner.nextInt();
        long b = scanner.nextInt();
        long sum = 1L;

        for (; a < b; a++) {
            sum *= a;
        }

        System.out.println(sum);
    }
}