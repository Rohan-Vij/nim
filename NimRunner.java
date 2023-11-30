public class NimRunner {
    public static void main(String[] args) {
        Nim game = new Nim("Player 1", "Player 2", 10);
        game.play();
    }
}
