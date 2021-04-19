package battleship;

import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private final Players player1;
    private final Players player2;

    public Game() {
        player1 = new Players("Player 1");
        player2 = new Players("Player 2");
        Players.init_BattleField(player1, player2);
    }

    public void start_game() {
        execute();
        scanner.close();
        Players.scanner.close();
        player1.getBattleField().scanner.close();
        player2.getBattleField().scanner.close();

    }

    private void execute() {

        boolean player1Turn = true;
        BattleField.SHOT response;

        do {
            if (player1Turn) {
                response = player1.execute(player2.getBattleField());
                if (!response.equals(BattleField.SHOT.DESTROYED)) {
                    System.out.println("Press Enter and pass the move to another player");
                    scanner.nextLine();
                    System.out.println();
                }
                player1Turn = false;
            } else {
                response = player2.execute(player1.getBattleField());
                if (!response.equals(BattleField.SHOT.DESTROYED)) {
                    System.out.println("Press Enter and pass the move to another player");
                    scanner.nextLine();
                    System.out.println();
                }
                player1Turn = true;
            }
        } while(!response.equals(BattleField.SHOT.DESTROYED));
    }
}
