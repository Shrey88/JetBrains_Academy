import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner scanner = new Scanner(System.in);

        String city = scanner.nextLine();

        if(city.endsWith("burg")){
            System.out.println("true");
        }
        else {
            System.out.println("false");
        }
    }
}