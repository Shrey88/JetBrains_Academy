import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int i = 0;
        int arrSize = scanner.nextInt();
        int[] array = new int[arrSize];

        while (i < arrSize) {
            array[i] = scanner.nextInt();
            ++i;
        }

        i = 0;
        int temp1 = array[i];
        array[i] = array[arrSize - 1];

        int temp2;
        while (i < arrSize - 1) {
            temp2 = array[i + 1];
            array[i + 1] = temp1;
            temp1 = temp2;
            ++i;
        }

        i = 0;
        while (i < arrSize) {
            System.out.print(array[i] + " ");
            ++i;
        }
    }
}