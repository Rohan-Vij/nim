package algorithms;

public class Binary implements IAlgorithm {
    @Override
    public int execute(int pileSize) {
        if (pileSize <= 1) {
            return 1;
        }

        int largestPowerOf2MinusOne = Integer.highestOneBit(pileSize) - 1;
        int optimalMove = pileSize - largestPowerOf2MinusOne;

        if (optimalMove > pileSize / 2) {
            optimalMove = pileSize / 2;
        }

        return optimalMove > 0 ? optimalMove : 1;
    }
}
