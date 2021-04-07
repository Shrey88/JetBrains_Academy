import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int arrSize = scanner.nextInt();
        int[] array = new int[arrSize];
        int triples = 0;

        int i = 0;
        while (i < arrSize) {
            array[i] = scanner.nextInt();
            ++i;
        }

        /**
         * check for the triples
         */
        int count;
        for (i = 0; i < arrSize; i++) {
            count = 0;

            for (int j = i; j < (i + 2) && (i + 2) < arrSize; j++) {
                if (array[j] + 1 == array[j + 1]) {
                    ++count;
                } else {
                    --count;
                }
            }

            if (count == 2) {
                ++triples;
            }
        }

        System.out.println(triples);
    }
}