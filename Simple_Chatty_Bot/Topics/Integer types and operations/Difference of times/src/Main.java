import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int hour1 = scanner.nextInt();
        int min1 = scanner.nextInt();
        int sec1 = scanner.nextInt();

        int hour2 = scanner.nextInt();
        int min2 = scanner.nextInt();
        int sec2 = scanner.nextInt();

        int diffHr = hour2 - hour1;
        int diffMin = min2 - min1;
        int diffSec = sec2 - sec1;

        System.out.println((diffHr * 3600) + (diffMin * 60) + diffSec);
    }
}