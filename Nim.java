import java.util.Scanner;

public class Nim {
    private Player player1;
    private Player player2;
    private Pile pile;
    private int turn;
    private Scanner scanner;

    public static final String ANSI_RESET = "\u001B[0m"; 
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BOLD = "\033[0;1m";

    public Nim(String player1Name, String player2Name, int pileSize) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        this.pile = new Pile(pileSize);
        this.turn = 1;
        this.scanner = new Scanner(System.in);
    }

    public void changeTurn() {
        this.turn ^= 1; // Bitwise XOR to change turn
    }

    public void play() {
        while (this.pile.getSize() > 0) {
            System.out.println("\n" + ANSI_BOLD + ANSI_GREEN + "Current pile size: " + this.pile.getSize() + ANSI_RESET);
            System.out.print(ANSI_YELLOW + (this.turn == 1 ? this.player1.getName() : this.player2.getName()) + ", enter number of items to remove: " + ANSI_RESET);

            int maxItemsToRemove = this.pile.getSize()/2;
            if (this.pile.getSize() == 1) {
                maxItemsToRemove = 1;
            }

            int itemsToRemove = scanner.nextInt();

            while (itemsToRemove <= 0 || itemsToRemove > maxItemsToRemove) {
                System.out.print(ANSI_BOLD + ANSI_RED + "Invalid number of items. " + ANSI_RESET + ANSI_YELLOW + "Please enter a number between 1 and " + this.pile.getSize()/2 + ": " + ANSI_RESET);
                itemsToRemove = scanner.nextInt();
            }

            this.pile.removeItems(itemsToRemove);
            changeTurn();
        }

        changeTurn(); // To determine the winner
        System.out.println(ANSI_BOLD + (this.turn == 1 ? this.player1.getName() : this.player2.getName()) + " wins!" + ANSI_RESET);
        scanner.close();
    }
}
