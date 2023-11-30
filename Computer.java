public class Computer extends Player {
    public Computer() {
        super("Computer");
    }

    public int algorithm(int pileSize) {
        if (pileSize <= 1) {
            return 1;
        }

        int bitLength = Integer.toBinaryString(pileSize).length();
        for (int i = 1; i < pileSize; i++) {
            int newPileSize = pileSize - i;
            if (Integer.bitCount(newPileSize ^ (newPileSize - 1)) < bitLength) {
                return i;
            }
        }
        return 1;
    }
}
