import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        double celsius = scanner.nextDouble();
        System.out.println(celsius * 1.8 + 32);
    }
}