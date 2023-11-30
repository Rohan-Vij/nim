public class Pile {
    private int size;

    public Pile(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    private int setSize(int size) {
        this.size = size;
        return this.size;
    }

    public int removeItems(int itemsToRemove) {
        return this.setSize(this.size - itemsToRemove);
    }
    
}
