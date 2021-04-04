import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int minHr = scanner.nextInt();
        int maxHr = scanner.nextInt();
        int sleepHr = scanner.nextInt();

        if (sleepHr < minHr) {
            System.out.println("Deficiency");
        } else if (sleepHr > maxHr) {
            System.out.println("Excess");
        } else {
            System.out.println("Normal");
        }
    }
}