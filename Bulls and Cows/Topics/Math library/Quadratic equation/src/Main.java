import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double eq = Math.sqrt(Math.pow(b, 2) - 4 * a * c);
        double denominator = 2 * a;
        double x1 = (-b + eq) / denominator;
        double x2 = (-b - eq) / denominator;

        System.out.print((x1 < x2 ? x1 : x2) + " " + (x2 > x1 ? x2 : x1));
    }
}