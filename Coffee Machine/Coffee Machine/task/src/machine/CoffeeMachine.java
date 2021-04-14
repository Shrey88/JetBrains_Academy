package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private static final Scanner scanner = new Scanner(System.in);
    private static int water = 400;
    private static int milk = 540;
    private static int coffeeBeans = 120;
    private static int disposableCups = 9;
    private static int money = 550;

    private static void coffeeMachineInfo() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffeeBeans + " of coffee beans");
        System.out.println(disposableCups + " of disposable cups");
        System.out.println(money + " of money");
    }
    private static void ingredients(int nCups) {
        System.out.println("For " + nCups + " cups of coffee you will need:");
        System.out.println((nCups * 200) + " ml of water");
        System.out.println((nCups * 50) + " ml of milk");
        System.out.println((nCups * 15) + " g of coffee beans");
    }

    public static void main(String[] args) {
//        System.out.println("Write how many cups of coffee you will need:");
//        int nCups = scanner.nextInt();
//        ingredients(nCups);

        System.out.println("Write how many ml of water the coffee machine has:");
        int water = scanner.nextInt() / 200;
        System.out.println("Write how many ml of milk the coffee machine has:");
        int milk = scanner.nextInt() / 50;
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int coffeeBeans = scanner.nextInt() / 15;
        System.out.println("Write how many cups of coffee you will need:");
        int noOfCups = scanner.nextInt();

        if (water == 0 || milk == 0 || coffeeBeans == 0) {
            System.out.println("No, I can make only 0 cups(s) of coffee");
        } else if (water > 0 && water <= milk && water <= coffeeBeans ) {
            if (water < noOfCups) {
                System.out.println("No, I can make only " + water + " cups(s) of coffee");
            } else if (water > noOfCups) {
                System.out.println("Yes, I can make that amount of coffee (and even " + (water - noOfCups) +
                        " more than that)");
            } else if (water == noOfCups) {
                System.out.println("Yes, I can make that amount of coffee");
            }
        } else if (milk > 0 && milk <= water && milk <= coffeeBeans) {
            if (milk < noOfCups) {
                System.out.println("No, I can make only " + milk + " cups(s) of coffee");
            } else if (milk > noOfCups) {
                System.out.println("Yes, I can make that amount of coffee (and even " + (milk - noOfCups) +
                        " more than that)");
            } else if (milk == noOfCups) {
                System.out.println("Yes, I can make that amount of coffee");
            }
        } else if (coffeeBeans > 0 && coffeeBeans <= water && coffeeBeans <= milk) {
            if (coffeeBeans < noOfCups) {
                System.out.println("No, I can make only " + coffeeBeans + " cups(s) of coffee");
            } else if (coffeeBeans > noOfCups) {
                System.out.println("Yes, I can make that amount of coffee (and even " + (coffeeBeans - noOfCups) +
                        " more than that)");
            } else if (coffeeBeans == noOfCups) {
                System.out.println("Yes, I can make that amount of coffee");
            }
        }
    }
}
