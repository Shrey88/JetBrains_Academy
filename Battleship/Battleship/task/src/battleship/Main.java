package battleship;

public class Main {

    public static void main(String[] args) {
        // Write your code here
        try {
            Game game = new Game();
            game.start_game();

        } catch (Exception e) {
            System.out.println("An error occurred : " + e.getMessage());
        }
    }
}
