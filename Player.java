public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String setName(String name) {
        this.name = name;
        return this.name;
    }
}
