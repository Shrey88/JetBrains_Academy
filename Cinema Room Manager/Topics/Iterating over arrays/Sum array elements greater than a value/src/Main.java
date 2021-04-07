import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int arrSize = scanner.nextInt();
        int[] array = new int[arrSize];

        int i = 0;
        int sum = 0;

        while (i < arrSize) {
            array[i] = scanner.nextInt();
            ++i;
        }

        int n = scanner.nextInt();
        i = 0;

        while (i < arrSize) {
            if (array[i] > n) {
                sum += array[i];
            }
            ++i;
        }
        System.out.println(sum);
    }
}