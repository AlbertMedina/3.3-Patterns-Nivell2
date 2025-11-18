package room;

public class Room {

    private int id;
    private int escapeRoomId;

    private final String name;
    private final Difficulty difficulty;
    private final double price;

    public Room(String name, Difficulty difficulty, double price) {
        this.name = name;
        this.difficulty = difficulty;
        this.price = price;
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

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public double getPrice() {
        return price;
    }

    public int getEscapeRoomId() {
        return escapeRoomId;
    }

    public void setEscapeRoomId(int escapeRoomId) {
        this.escapeRoomId = escapeRoomId;
    }

    public void addHint() {

    }

    public void removeHint(int id) {

    }

    public void addDecoration() {

    }

    public void removeDecoration(int id) {

    }
}
