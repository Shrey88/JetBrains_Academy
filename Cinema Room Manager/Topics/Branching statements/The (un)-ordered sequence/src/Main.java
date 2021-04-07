import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        boolean asc = true;
        boolean desc = true;

        int prev = scanner.nextInt();
        int next;

        while (scanner.hasNextInt()) {
            next = scanner.nextInt();

            if (prev == next) {
                prev = next;
                continue;
            }

            if (prev <= next && asc) {
                prev = next;
                desc = false;
            } else if (!desc) {
                if (next == 0) {
                    System.out.println("true");
                } else {
                    System.out.println("false");
                }
                break;
            }

            if (prev >= next && desc) {
                prev = next;
                asc = false;
                if (next == 0) {
                    System.out.println("true");
                    break;
                }
            } else if (!asc) {
                if (next == 0) {
                    System.out.println("true");
                } else {
                    System.out.println("false");
                }
                break;
            }


        }
    }
}