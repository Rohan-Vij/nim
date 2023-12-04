import java.util.Scanner;

import algorithms.AlgorithmFactory;
import algorithms.IAlgorithm;

import java.util.Map;
import java.util.Random;
import java.sql.Array;
import java.util.ArrayList;

public class Nim {
    protected Player player1;
    protected Player player2;
    private Pile pile;
    private int turn;
    private Scanner scanner;
    private Random random;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BOLD = "\033[0;1m";

    public Nim() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.pile = new Pile(random.nextInt(31) + 20); // Random pile size between 20 and 50
        this.turn = (int)(Math.random()*2); // Random int between 0 and 1 to randomize who starts the game
        setupPlayers();
    }

    protected void setupPlayers() {
        this.player1 = setupPlayer(1);
        this.player2 = setupPlayer(2);
    }

    private Player setupPlayer(int playerNumber) {
        System.out.print(ANSI_BLUE + "Enter name for Player " + playerNumber + " (leave blank for Computer): " + ANSI_RESET);
        String playerName = scanner.nextLine().trim();

        if (playerName.isEmpty()) {
            return setupComputerPlayer(playerNumber);
        } else {
            return new Player(playerName);
        }
    }

    private Player setupComputerPlayer(int playerNumber) {
        System.out.println(ANSI_BOLD + "\n----------------------------------" + ANSI_RESET);
        Map<Integer, String> algorithmList = AlgorithmFactory.getAlgorithmList();
        System.out.println(ANSI_BLUE + "Select an algorithm for Computer " + playerNumber + ":" + ANSI_RESET);

        for (Map.Entry<Integer, String> entry : algorithmList.entrySet()) {
            System.out.println(ANSI_YELLOW + "ID: " + entry.getKey() + ANSI_RESET + " - "  + ANSI_BOLD + ANSI_GREEN + entry.getValue() + ANSI_RESET);
        }

        System.out.print(ANSI_YELLOW + "Enter algorithm ID: " + ANSI_RESET);
        int algorithmId = scanner.nextInt();
        scanner.nextLine(); // consume the rest of the line

        System.out.println(ANSI_BOLD + "\n----------------------------------\n" + ANSI_RESET);

        IAlgorithm selectedAlgorithm = AlgorithmFactory.getAlgorithm(algorithmId);
        
        return new Computer(playerNumber, selectedAlgorithm);
    }

    public void changeTurn() {
        this.turn ^= 1; // Bitwise XOR to change turn
    }

    public ArrayList<Object> play() {
        ArrayList<Object> information = new ArrayList<Object>();

        System.out.println(ANSI_RED + "Starting the game! " + ANSI_BOLD + ANSI_RED + (this.turn == 1 ? this.player1 : this.player2).getName() + ANSI_RESET + ANSI_RED + " goes first!" + ANSI_RESET);

        information.add(this.pile.getSize());

        Player starting = this.turn == 1 ? this.player1 : this.player2;
        information.add(starting.getName());

        while (this.pile.getSize() > 0) {
            Player currentPlayer = this.turn == 1 ? this.player1 : this.player2;
            String currentPlayerName = currentPlayer.getName();
            System.out.println("\n" + ANSI_BOLD + ANSI_GREEN + "Current pile size: " + this.pile.getSize() + ANSI_RESET);
    
            int maxItemsToRemove = this.pile.getSize() == 1 ? 1 : this.pile.getSize() / 2;
            int itemsToRemove = 0;

            System.out.print(ANSI_YELLOW + currentPlayerName + ", enter number of items to remove (1-" + maxItemsToRemove + "): " + ANSI_RESET);

    
            if (currentPlayer instanceof Computer) {
                itemsToRemove = ((Computer) currentPlayer).execute(this.pile.getSize());
                System.out.println(currentPlayerName + " removes " + itemsToRemove + " item" + (itemsToRemove > 1 ? "s" : "") + ".");
            } else {
                itemsToRemove = scanner.nextInt();
                while (itemsToRemove <= 0 || itemsToRemove > maxItemsToRemove) {
                    System.out.print(ANSI_BOLD + ANSI_RED + "Invalid number of items. " + ANSI_RESET + ANSI_YELLOW + "Please enter a number between 1 and " + maxItemsToRemove + ": " + ANSI_RESET);
                    itemsToRemove = scanner.nextInt();
                }
            }
    
            this.pile.removeItems(itemsToRemove);
            changeTurn();
        }
    
        // Announce the winner
        changeTurn();
        Player winningPlayer = this.turn == 1 ? this.player2 : this.player1;
        information.add(winningPlayer.getName());

        System.out.println("\n" + ANSI_BOLD + ANSI_BLUE + "***" + winningPlayer.getName() + " wins!***" + ANSI_RESET);
        scanner.close();

        return information;
    }
}
