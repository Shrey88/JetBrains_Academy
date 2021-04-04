import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        double start = scanner.nextDouble();
        double end = scanner.nextDouble();
        double sum = 0;
        int intervals = 0;
        for (; start <= end; start++) {
            if (start % 3 == 0) {
                sum += start;
                ++intervals;
            }
        }

        System.out.println(sum / intervals);
    }
}