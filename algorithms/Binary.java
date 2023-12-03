package algorithms;

public class Binary implements IAlgorithm {
    @Override
    public int execute(int pileSize) {
        if (pileSize <= 1) {
            return 1;
        }

        int largestPowerOf2 = Integer.highestOneBit(pileSize);
        int optimalMove = pileSize - (pileSize ^ largestPowerOf2);

        return optimalMove > 0 ? optimalMove : 1;
    }
}
