import java.math.BigInteger;

class DoubleFactorial {
    public static BigInteger calcDoubleFactorial(int n) {
        // type your java code here
        BigInteger result = new BigInteger(String.valueOf(n));

        if (n % 2 == 0 && n > 1) {
            for (int i = n - 2; i >= 2;) {
                result = result.multiply(new BigInteger(String.valueOf(i)));
                i = i - 2;
            }
        } else if (n % 2 != 0 && n > 1) {
            for (int i = n - 2; i >= 1;) {
                result = result.multiply(new BigInteger(String.valueOf(i)));
                i = i - 2;
            }
        } else if (n == 0 || n == 1) {
            result = BigInteger.ONE;
        }

        return result;
    }
}