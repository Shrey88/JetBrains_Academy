import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int iCups = scanner.nextInt();
        boolean isWeekend = scanner.nextBoolean();

        if (iCups >= 10 && iCups <= 20 && !isWeekend) {
            System.out.println("true");
        } else if (iCups >= 15 && iCups <= 25 && isWeekend) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}