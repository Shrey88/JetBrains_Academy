import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int s1 = scanner.nextInt();
        int s2 = scanner.nextInt();
        int s3 = scanner.nextInt();

        int iBenches = 0;

        iBenches += s1 % 2;
        iBenches += s1 / 2;

        iBenches += s2 % 2;
        iBenches += s2 / 2;

        iBenches += s3 % 2;
        iBenches += s3 / 2;

        System.out.println(iBenches);
    }
}