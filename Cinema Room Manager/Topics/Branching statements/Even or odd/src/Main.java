import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        while (input != 0) {
            if (input % 2 == 0) {
                System.out.println("even");
            } else {
                System.out.println("odd");
            }
            input = scanner.nextInt();
        }
    }
}