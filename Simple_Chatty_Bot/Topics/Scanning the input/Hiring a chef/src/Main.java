//put imports you need here

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scan = new Scanner(System.in);

        String name = scan.next();
        scan.next();
        scan.next();
        scan.next();
        String cuisinePreference = scan.next();

        System.out.println("The form for " + name + " is completed. We will contact you if we need a chef that cooks " +
                cuisinePreference + " dishes.");
    }
}