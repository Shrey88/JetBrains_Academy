package battleship;

import java.util.Scanner;

class Players {
    protected static final Scanner scanner = new Scanner(System.in);
    private final String playerName;
    static final Battleship battleship = new Battleship();
    private final BattleField battleField;

    public BattleField getBattleField() {
        return battleField;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Players(String playerName) {
        this.battleField = new BattleField();
        this.playerName = playerName;
    }

    public BattleField.SHOT execute(BattleField battleField) {
        fog_of_war(battleField);
        return takeShot(battleField);
    }

    /**
     * pass the object of the other battlefield on which you want to take a shot.
     *
     * @param battleField enemies battlefield
     */
    private void fog_of_war(BattleField battleField) {

        for(int i = 0; i < battleField.battleField.length; i++) {
            if (i == 0) {
                System.out.print(" ");
            } else {
                System.out.print(battleField.battleField[i][0]);
            }

            for (int j = 1; j < battleField.battleField[0].length; j++) {
                if (i == 0) {
                    System.out.print(" " + battleField.battleField[i][j]);
                } else {
                    System.out.print(" ~");
                }
            }
            System.out.println();
        }

        System.out.println("---------------------");

        for(int i = 0; i < this.battleField.battleField.length; i++) {
            if (i == 0) {
                System.out.print(" ");
            } else {
                System.out.print(this.battleField.battleField[i][0]);
            }

            for (int j = 1; j < this.battleField.battleField[0].length; j++) {
                if (i == 0) {
                    System.out.print(" " + this.battleField.battleField[i][j]);
                } else {
                    if ("X".equals(this.battleField.battleField[i][j])) {
                        System.out.print(" " + this.battleField.battleField[i][j]);
                    } else if ("M".equals(this.battleField.battleField[i][j])) {
                        System.out.print(" " + this.battleField.battleField[i][j]);
                    } else {
                        System.out.print(" " + (!"~".equals(this.battleField.battleField[i][j]) ?
                                "O" : this.battleField.battleField[i][j]));
                    }
                }
            }
            System.out.println();
        }

        System.out.println("\n"+this.getPlayerName() + ", It's your turn:\n");
    }

    /**
     * calling this method will ask the player to place their ships on their battlefield.
     * @param p1 player1 has to place his ships on the battlefield
     * @param p2 player2 has to place his ships on the battlefield
     */
    protected static void init_BattleField(Players p1, Players p2) {

        System.out.println(p1.getPlayerName() + ", place your ships on the game field\n");
        battleship.placeBattleShip(p1.battleField);
        System.out.println("Press Enter and pass the move to another player\n");
        scanner.nextLine();
        System.out.println(p2.getPlayerName() + ", place your ships to the game field\n");
        battleship.placeBattleShip(p2.battleField);

        System.out.println("Press Enter and pass the move to another player\n");
        scanner.nextLine();
    }

    private BattleField.SHOT takeShot(BattleField enemyBattleField) {
        final Scanner scanner = new Scanner(System.in);

        String coordinate = scanner.next();
        System.out.println();
        int xPos = Integer.parseInt(String.valueOf(coordinate.charAt(1)));
        if (coordinate.length() == 3) {
            String temp = String.valueOf(coordinate.charAt(1)) + coordinate.charAt(2);
            xPos = Integer.parseInt(temp);
        }

        if (BattleField.VERTICAL_POS.constantExists(String.valueOf(coordinate.charAt(0))) == null ||
                xPos > 10) {
            System.out.println("Error! You entered the wrong coordinates! Try again:\n");
            takeShot(enemyBattleField);
        } else {
            BattleField.SHOT response = enemyBattleField.fireShot(coordinate);

            if (enemyBattleField.getAircraftCarrierHit() == 0 &&
                    enemyBattleField.getBattleshipHit() == 0 &&
                    enemyBattleField.getSubmarineHit() == 0 &&
                    enemyBattleField.getCruiserHit() == 0 &&
                    enemyBattleField.getDestroyerHit() == 0) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                return BattleField.SHOT.DESTROYED;
            } else if (response.equals(BattleField.SHOT.HIT)) {
                System.out.println("You hit a ship! Try again:\n");
            } else if (response.equals(BattleField.SHOT.MISS)) {
                System.out.println("You missed. Try again:\n");
            } else if (response.equals(BattleField.SHOT.SANK)) {
                System.out.println("You sank a ship! Specify a new target:\n");
            }
            return response;
        }
        return null;
    }
}


