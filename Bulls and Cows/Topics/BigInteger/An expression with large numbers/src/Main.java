import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String a = scanner.next();
        String b = scanner.next();
        String c = scanner.next();
        String d = scanner.next();

        BigInteger biA = new BigInteger(String.valueOf(a));
        BigInteger biB = new BigInteger(String.valueOf(b));
        BigInteger biC = new BigInteger(String.valueOf(c));
        BigInteger biD = new BigInteger(String.valueOf(d));

        BigInteger result = biA.negate().multiply(biB).add(biC).subtract(biD);
        System.out.println(result);
    }
}