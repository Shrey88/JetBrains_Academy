package machine;

import java.util.Scanner;

public class CoffeeMachine {
    enum Action { BUY, FILL, TAKE, REMAINING, EXIT };

    private static final Scanner scanner = new Scanner(System.in);
    private int water;
    private int milk;
    private int coffeeBeans;
    private int disposableCups;
    private int money;

    public CoffeeMachine() {
        this.water = 400;
        this.milk = 540;
        this.coffeeBeans = 120;
        this.disposableCups = 9;
        this.money = 550;
    }

    public void menu() {
        boolean isContinue = true;

        do {
            System.out.println("Write action (buy, fill, take):");
            Action action = Action.valueOf(scanner.next().toUpperCase());
            System.out.println();

            switch(action) {
                case BUY:
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                    String option = scanner.next();
                    if ("1".equals(option) || "2".equals(option) || "3".equals(option)) {
                        makeCoffee(option);
                    }
                    break;
                case FILL:
                    refillCoffeeMachine();
                    break;
                case TAKE:
                    removeCash();
                    break;
                case REMAINING:
                    coffeeMachineInfo();
                    break;
                case EXIT:
                    isContinue = false;
                    break;
                default:
                    break;
            }
        } while (isContinue);
    }

    private void coffeeMachineInfo() {
        System.out.println("The coffee machine has:");
        System.out.println(this.water + " of water");
        System.out.println(this.milk + " of milk");
        System.out.println(this.coffeeBeans + " of coffee beans");
        System.out.println(this.disposableCups + " of disposable cups");
        System.out.println(this.money + " of money\n");
    }

    private void makeCoffee(String option) {

        switch(option) {
            case "1":
                if (water >= 250 && coffeeBeans >= 16) {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    water -= 250;
                    coffeeBeans -= 16;
                    money += 4;
                    --disposableCups;
                } else if (water < 250 && coffeeBeans >= 16) {
                    System.out.println("Sorry, not enough water!\n");
                } else if (water >= 250 && coffeeBeans < 16) {
                    System.out.println("Sorry, not enough coffee beans!\n");
                } else if (water < 250 && coffeeBeans < 16) {
                    System.out.println("Sorry, not enough water and coffee beans!\n");
                }
                break;
            case "2":
                if (water >= 350 && milk >= 75 && coffeeBeans >=20) {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    water -= 350;
                    milk -= 75;
                    coffeeBeans -= 20;
                    money += 7;
                    --disposableCups;
                } else if (water < 250 && milk >= 75 && coffeeBeans >= 20) {
                    System.out.println("Sorry, not enough water!\n");
                } else if (water >= 250 && milk < 75 && coffeeBeans >= 20) {
                    System.out.println("Sorry, not enough milk!\n");
                } else if (water >= 250 && milk >= 75 && coffeeBeans < 20) {
                    System.out.println("Sorry, not enough coffee beans!\n");
                } else if (water < 250 && milk < 75 && coffeeBeans < 20) {
                    System.out.println("Sorry, not enough water, milk and coffee beans!\n");
                }
                break;
            case "3":
                if (water >= 200 && milk >= 100 && coffeeBeans >= 12) {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    water -= 200;
                    milk -= 100;
                    coffeeBeans -= 12;
                    money += 6;
                    --disposableCups;
                } else if (water < 200 && milk >= 100 && coffeeBeans >= 12) {
                    System.out.println("Sorry, not enough water!\n");
                } else if (water >= 200 && milk < 100 && coffeeBeans >= 12) {
                    System.out.println("Sorry, not enough milk!");
                } else if (water >= 200 && milk >= 100 && coffeeBeans < 12) {
                    System.out.println("Sorry, not enough coffee beans!\n");
                } else if (water < 200 && milk < 100 && coffeeBeans < 12) {
                    System.out.println("Sorry, not enough water, milk and coffee beans!\n");
                }
                break;
            default:
                break;
        }
    }

    private void refillCoffeeMachine() {
        System.out.println("Write how many ml of water do you want to add:");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        coffeeBeans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        disposableCups += scanner.nextInt();
        System.out.println();
    }

    private void removeCash() {
        System.out.println("I gave you $" + money + "\n");
        money = 0;
    }


}
