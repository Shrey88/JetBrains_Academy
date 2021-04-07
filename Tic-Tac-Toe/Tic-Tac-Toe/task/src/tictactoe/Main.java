package tictactoe;

import java.util.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final char[][] grid = new char[3][3];
    private static final ArrayList<Integer> xList = new ArrayList<>();
    private static final ArrayList<Integer> oList = new ArrayList<>();
    private static boolean xMove = true;
    private static boolean oMove = false;
    private static int _Count = 0;
    private static boolean xWins = false;
    private static boolean oWins = false;
    private static boolean draw = false;

    private static void displayGrid() {
        System.out.println("---------");
        for (int i = 0; i < grid.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    /*
     *  input string format XXX0__00_
     *  values can only be X or O or _/space
     *  each character will be stored in the char[][]
     *
     *  if a null input is passed then default grid will be created
     */
    private static void setupGrid(String cells) {
        int index = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (cells != null) {
                    grid[i][j] = cells.charAt(index);
                    if (cells.charAt(index) == 'X') {
                        if (i == 0) {
                            xList.add(j + 1);   // marking the position in 1st row (1 2 3) and adding that position in a list
                        } else if (i == 1) {
                            xList.add(j + 4);   // marking the position in 2nd row (4 5 6) and adding that position in a list
                        } else {
                            xList.add(j + 7);   // marking the position in 3rd row (7 8 9) and adding that position in a list
                        }
                    } else if (cells.charAt(index) == 'O') {
                        if (i == 0) {
                            oList.add(j + 1);   // marking the position in 1st row (1 2 3) and adding that position in a list
                        } else if (i == 1) {
                            oList.add(j + 4);   // marking the position in 2nd row (4 5 6) and adding that position in a list
                        } else {
                            oList.add(j + 7);   // marking the position in 3rd row (7 8 9) and adding that position in a list
                        }
                    } else if (cells.charAt(index) == '_' || cells.charAt(index) == ' ') {
                        ++_Count;
                    }
                    ++index;
                } else {
                    grid[i][j] = ' ';
                    ++_Count;
                }
            }
        }
    }

    private static void userMove() {
        System.out.print("Enter the coordinates: ");
        String row = scanner.next();
        /*
         * check if the variable contains only integer values and return true.
         */
        if (!Pattern.matches("\\d", row)) {
            System.out.println("You should enter numbers!");
            scanner.nextLine();
            userMove();
            return;
        }
        String col = scanner.next();
        if (!Pattern.matches("\\d", col)) {
            System.out.println("You should enter numbers!");
            scanner.nextLine();
            userMove();
            return;
        }

        int iRow = Integer.parseInt(row);
        int iCol = Integer.parseInt(col);

        /*
         *  check if the given co-ordinates are valid.
         */
        if ((iRow > 0 && iRow <= 3) && (iCol > 0 && iCol <= 3 )) {

            /*
             *  check if the given coordinate is available.
             */
            if (grid[iRow - 1][iCol - 1] == '_' || grid[iRow - 1][iCol - 1] == ' '){

                if (xMove) {
                    grid[iRow - 1][iCol - 1] = 'X';

                    /*
                     *  Update the position in the list
                     */
                    if (iRow - 1 == 0) {
                        xList.add(iCol);   // marking the position in 1st row (1 2 3) and adding that position in a list
                    } else if (iRow - 1 == 1) {
                        xList.add(iCol - 1 + 4);   // marking the position in 2nd row (4 5 6) and adding that position in a list
                    } else {
                        xList.add(iCol - 1 + 7);   // marking the position in 3rd row (7 8 9) and adding that position in a list
                    }

                    /*
                     *  Changing the user's move
                     */
                    xMove = false;
                    oMove = true;
                } else if (oMove) {
                    grid[iRow - 1][iCol - 1] = 'O';

                    /*
                     *  Update the position in the list
                     */
                    if (iRow - 1 == 0) {
                        oList.add(iCol);   // marking the position in 1st row (1 2 3) and adding that position in a list
                    } else if (iRow - 1 == 1) {
                        oList.add(iCol - 1 + 4);   // marking the position in 2nd row (4 5 6) and adding that position in a list
                    } else {
                        oList.add(iCol - 1 + 7);   // marking the position in 3rd row (7 8 9) and adding that position in a list
                    }

                    /*
                     *  Changing the user's move
                     */
                    xMove = true;
                    oMove = false;
                }
                --_Count;
                displayGrid();
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                userMove();
            }
        } else {
            System.out.println("Coordinate should be from 1 to 3!");
            userMove();
        }
    }

    private static void checkResult() {
        boolean xResult = false;
        boolean oResult = false;

        /*
         * find the match of 3 X's in vertical / horizontal / diagonal
         *
         *  ---------
         *  | 1 2 3 |
         *  | 4 5 6 |
         *  | 7 8 9 |
         *  ---------
         *
         */
        if (xList.contains(1) && xList.contains(2) && xList.contains(3)) {
            xResult = true;
        } else if (xList.contains(4) && xList.contains(5) && xList.contains(6)) {
            xResult = true;
        } else if (xList.contains(7) && xList.contains(8) && xList.contains(9)) {
            xResult = true;
        } else if (xList.contains(1) && xList.contains(4) && xList.contains(7)) {
            xResult = true;
        } else if (xList.contains(2) && xList.contains(5) && xList.contains(8)) {
            xResult = true;
        } else if (xList.contains(3) && xList.contains(6) && xList.contains(9)) {
            xResult = true;
        } else if ((xList.contains(1) && xList.contains(9) || xList.contains(1) && xList.contains(3))
                && xList.contains(5)) {
            xResult = true;
        }

        /*
         * find the match of 3 O's in vertical / horizontal / diagonal
         *
         *  ---------
         *  | 1 2 3 |
         *  | 4 5 6 |
         *  | 7 8 9 |
         *  ---------
         *
         */
        if (oList.contains(1) && oList.contains(2) && oList.contains(3)) {
            oResult = true;
        } else if (oList.contains(4) && oList.contains(5) && oList.contains(6)) {
            oResult = true;
        } else if (oList.contains(7) && oList.contains(8) && oList.contains(9)) {
            oResult = true;
        } else if (oList.contains(1) && oList.contains(4) && oList.contains(7)) {
            oResult = true;
        } else if (oList.contains(2) && oList.contains(5) && oList.contains(8)) {
            oResult = true;
        } else if (oList.contains(3) && oList.contains(6) && oList.contains(9)) {
            oResult = true;
        } else if ((oList.contains(1) && oList.contains(9) || oList.contains(1) && oList.contains(3))
                && oList.contains(5)) {
            oResult = true;
        }

        if (xResult && !oResult) {
            System.out.println("X wins");
            xWins = true;
        } else if (!xResult && oResult) {
            System.out.println("O wins");
            oWins = true;
        } else if (!xResult && !oResult && _Count == 0) {
            System.out.println("Draw");
            draw = true;
        } else if ((xResult && oResult) || (xList.size() > oList.size() || xList.size() < oList.size())) {
            //System.out.println("Impossible");
        } else if (!xResult && !oResult && _Count > 0) {
            //System.out.println("Game not finished");
        }
    }

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);

//        System.out.print("Enter cells: ");
//        String cells = scanner.nextLine();
        setupGrid(null);
        displayGrid();
        while (_Count != 0 && !xWins && !oWins && !draw) {
            userMove();
            checkResult();
        }
    }
}
