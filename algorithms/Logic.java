package algorithms;

public class Logic implements IAlgorithm {

    @Override
    public int execute(int pileSize) {
        if (pileSize <= 1) {
            return 1;
        }

        int toRemove;

        // Decides how many sticks are needed to win
        double pow = Math.floor(Math.log(pileSize) / Math.log(2));
        double idealNum = Math.pow(2, pow) - 1;
    
        // Changes it to an int for no type error
        int idealNum2 = (int) Math.round(idealNum);
    
        toRemove = pileSize - idealNum2;
        // Checks if toRemove is greater than the maximum amount of sticks you can
        // remove
        if (toRemove > pileSize / 2) {
          toRemove = pileSize / 2;
        }
    
        // returns the amount of sticks the ai wants to return
        return toRemove;
    }
    
}
