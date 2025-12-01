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

        if (price <= 0) {
            throw new IllegalArgumentException("Invalid price");
        }

        this.name = name;
        this.difficulty = difficulty;
        this.price = price;
        this.escapeRoomId = escapeRoomId;
    }

    public Room(String name, int difficultyValue, double price, int escapeRoomId) {
        this(name, Difficulty.fromInt(difficultyValue), price, escapeRoomId);
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
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.name = name;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficultyValue) {
        setDifficulty(Difficulty.fromInt(difficultyValue));
    }

    public void setDifficulty(Difficulty difficulty) {
        if (difficulty == null) {
            throw new IllegalArgumentException("Invalid difficulty");
        }
        this.difficulty = difficulty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Invalid price");
        }
        this.price = price;
    }

    public int getEscapeRoomId() {
        return escapeRoomId;
    }

    public void setEscapeRoomId(int escapeRoomId) {
        this.escapeRoomId = escapeRoomId;
    }

    @Override
    public String toString() {
        return "Room ID" + id + " -> Name: " + name + " | Difficulty: " + difficulty.toString().toLowerCase() + " | Price: " + price + "€ | Escape room ID: " + escapeRoomId;
    }
}
