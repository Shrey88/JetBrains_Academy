import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int lower = scanner.nextInt();
        int upper = scanner.nextInt();
        int nPseudo = scanner.nextInt();
        int seed = scanner.nextInt();


        int highSeed = 0;
        int temp1 = 0;
        int temp2 = 0;
        int randomNumber;
        for (int i = lower; i <= upper; i++) {
//            System.out.print(i + " :\t");
            Random random = new Random(i);
            temp1 = 0;
            for (int j = 0; j < nPseudo; j++) {
                randomNumber = random.nextInt(seed);
//                System.out.print(randomNumber + "\t");

                if (temp1 < randomNumber) {
                    temp1 = randomNumber;
                }
            }

            System.out.println();
            if (i == lower) {
                temp2 = temp1;
                highSeed = i;
            } else if (temp1 < temp2) {
                temp2 = temp1;
                highSeed = i;
            }
        }

        System.out.println(highSeed);
        System.out.println(temp2);
    }
}