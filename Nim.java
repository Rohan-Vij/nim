import java.util.Scanner;

public class Nim {
    private Player player1;
    private Player player2;
    private int pileSize;
    private int turn;
    private Scanner scanner;

    public Nim(String player1Name, String player2Name, int pileSize) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        this.pileSize = pileSize;
        this.turn = 1;
        this.scanner = new Scanner(System.in);
    }

    public void changeTurn() {
        this.turn ^= 1; // Bitwise XOR to change turn
    }

    public void play() {
        while (this.pileSize > 0) {
            System.out.println("Current pile size: " + this.pileSize);
            System.out.print((this.turn == 1 ? this.player1.getName() : this.player2.getName()) + ", enter number of items to remove: ");

            int itemsToRemove = scanner.nextInt();
            while (itemsToRemove <= 0 || itemsToRemove > this.pileSize) {
                System.out.print("Invalid number of items. Please enter a number between 1 and " + this.pileSize + ": ");
                itemsToRemove = scanner.nextInt();
            }

            this.pileSize -= itemsToRemove;
            changeTurn();
        }

        changeTurn();
        if (this.turn == 1) {
            System.out.println(this.player1.getName() + " wins!");
        } else {
            System.out.println(this.player2.getName() + " wins!");
        }
        scanner.close();
    }   
}
