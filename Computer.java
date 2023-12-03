import algorithms.IAlgorithm;

public class Computer extends Player {
    private IAlgorithm algorithm;

    public Computer(int id, IAlgorithm algorithmChoice) {
        super("Computer" + id);
        this.algorithm = algorithmChoice;
    }

    public int execute(int pileSize) {
        return this.algorithm.execute(pileSize);
    }
}
