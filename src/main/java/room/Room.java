package room;

public class Room {

    private int id;
    private String name;
    private Difficulty difficulty;
    private double price;
    private int escapeRoomId;

    public Room(String name, Difficulty difficulty, double price, int escapeRoomId) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }

        if (difficulty == null) {
            throw new IllegalArgumentException("Invalid difficulty");
        }

        if (price >= 0) {
            throw new IllegalArgumentException("Invalid price");
        }

        this.name = name;
        this.difficulty = difficulty;
        this.price = price;
        this.escapeRoomId = escapeRoomId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getEscapeRoomId() {
        return escapeRoomId;
    }

    public void setEscapeRoomId(int escapeRoomId) {
        this.escapeRoomId = escapeRoomId;
    }
}
