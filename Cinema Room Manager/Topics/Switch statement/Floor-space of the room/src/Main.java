import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String shape = scanner.next();
        double a;
        double b;

        switch (shape) {
            case "triangle":
                a = scanner.nextDouble();
                b = scanner.nextDouble();
                double c = scanner.nextDouble();
                /*
                 * finding the parameter of triangle first
                 */
                double s = (a + b + c) / 2;

                /*
                 *  find the height
                 */
                double h = ((Math.sqrt(s * (s - a) * (s - b) * (s - c))) * 2) / b;
                System.out.println((h * b) / 2);
                break;
            case "rectangle":
                a = scanner.nextDouble();
                b = scanner.nextDouble();
                System.out.println(a * b);
                break;
            case "circle":
                double r = scanner.nextDouble();
                System.out.println(3.14 * r * r);
                break;
            default:
                System.out.println("Not a valid shape");
                break;
        }
    }
}