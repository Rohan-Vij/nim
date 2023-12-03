package algorithms;

public class Random implements IAlgorithm {
    @Override
    public int execute(int pileSize) {
        // Just removes as many as possible

        int maxItemsToRemove = pileSize == 1 ? 1 : pileSize / 2;

        return (int) (Math.random() * maxItemsToRemove) + 1;
    }
}
