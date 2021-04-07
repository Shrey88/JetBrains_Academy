import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int[][] arr = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        int swapCol1 = scanner.nextInt();
        int swapCol2 = scanner.nextInt();

        for (int i = 0; i < rows; i++) {
            int temp = arr[i][swapCol1];
            arr[i][swapCol1] = arr[i][swapCol2];
            arr[i][swapCol2] = temp;
            for (int j = 0; j < cols; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }
}