import java.util.List;
import java.util.ArrayList;

import algorithms.AlgorithmFactory;
import algorithms.IAlgorithm;

public class Head2Head extends Nim {
    private String algorithm1;
    private String algorithm2;
    private List<String> gameResults;
    
    public Head2Head(String algorithm1, String algorithm2) {
        super(); 
        this.algorithm1 = algorithm1;
        this.algorithm2 = algorithm2;
        this.gameResults = new ArrayList<String>();
        setupPlayers();
    }

    @Override
    protected void setupPlayers() {
        this.player1 = setupComputerPlayer(1, algorithm1);
        this.player2 = setupComputerPlayer(2, algorithm2);
    }

    private Player setupComputerPlayer(int playerNumber, String algorithmName) {
        IAlgorithm algorithm = AlgorithmFactory.getAlgorithmByName(algorithmName);
        return new Computer("Computer " + playerNumber + " - " + algorithmName, algorithm);
    }

    public static void main(String[] args) {
        String algorithm1 = "Binary"; // Default algorithm
        String algorithm2 = "Random"; // Default algorithm

        for (int i = 0; i < args.length; i++) {
            if ("-a1".equals(args[i]) && i + 1 < args.length) {
                algorithm1 = args[i + 1];
            }
            if ("-a2".equals(args[i]) && i + 1 < args.length) {
                algorithm2 = args[i + 1];
            }
        }

        Head2Head game = new Head2Head(algorithm1, algorithm2);
        game.play();
    }
}
