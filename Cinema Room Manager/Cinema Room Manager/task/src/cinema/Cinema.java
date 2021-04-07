package cinema;

import java.util.Scanner;

public class Cinema {
    public static Scanner scanner = new Scanner(System.in);
    private static int ticketsBooked = 0;
    private static int currIncome = 0;

    public static void showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

    }

    public static void setupCinema(String[][] cinema) {
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[0].length; j++) {
                cinema[i][j] = "S";
            }
        }
    }

    public static void showSeats(String[][] cinema) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 0; i < cinema[0].length; i++) {
            System.out.print(" " + (i + 1));
        }

        System.out.println();

        for (int i = 0; i < cinema.length; i++) {
            System.out.print((i + 1) +" ");

            for (int j = 0; j < cinema[0].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }

        /*
         *  to create some space after displaying the seats
         */
        System.out.println();
    }

    public static void bookTicket(int rows, int seats, String[][] cinema) {
        System.out.println("Enter a row number:");
        int rowSelected = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatSelected = scanner.nextInt();
        System.out.println();
        /*
         *   check for the out of the bound exception.
         */
        if ((rowSelected < 1 || rowSelected > rows) || (seatSelected < 1 || seatSelected > seats)) {
            System.out.println("Wrong input! \n");
            bookTicket(rows, seats, cinema);
            return;
        }

        // check for the seat availability and the book.
        if (cinema[rowSelected - 1][seatSelected - 1].equalsIgnoreCase("B")){
            System.out.println("That ticket has already been purchased!\n");
            bookTicket(rows, seats, cinema);
            return;
        } else {
            cinema[rowSelected - 1][seatSelected - 1] = "B";
            ++ticketsBooked;
        }


        if (rows * seats <= 60) {
            System.out.println("Ticket price: $10");
            currIncome = currIncome + 10;
        } else {
            int firstHalf = rows / 2;

            if (rowSelected <= firstHalf) {
                System.out.println("Ticket price: $10");
                currIncome = currIncome + 10;
            } else {
                System.out.println("Ticket price: $8");
                currIncome = currIncome + 8;
            }
        }

        /*
         * create some space after printing the price
         */
        System.out.println();
    }

    public static void showStatistics(int rows, int seats){
        System.out.println("Number of purchased tickets: " + ticketsBooked);
        System.out.println(String.format("Percentage: %.2f",(double) (ticketsBooked * 100) / (rows * seats)) + "%");
        System.out.println("Current income: $" + currIncome);
        if (rows * seats <= 60) {
            System.out.println("Total income: $" + (rows * seats * 10) + "\n");
        } else {
            int firstHalf = rows / 2;
            int secondHalf = rows - firstHalf;
            System.out.println("Total income: $" + ((firstHalf * seats * 10) + (secondHalf * seats * 8)) + "\n");
        }
    }

    public static void main(String[] args) {
        // Write your code here
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        System.out.println();

        String[][] cinema = new String[rows][seats];
        setupCinema(cinema);

        boolean continueBooking = true;
        int option;
        do {
            showMenu();
            option = scanner.nextInt();
            System.out.println();

            switch(option) {
                case 1:
                    showSeats(cinema);
                    break;
                case 2:
                    bookTicket(rows, seats, cinema);
                    break;
                case 3:
                    showStatistics(rows, seats);
                    break;
                case 0:
                    continueBooking = false;
                    break;
                default:
                    break;
            }
        } while(continueBooking);
    }
}