import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scan = new Scanner(System.in);

        long m = scan.nextLong();
        long n = 1L;
        long product = 1L;

        do {
            product *= n;
            ++n;
        } while (product <= m);

        System.out.println(n - 1);
    }
}