package decoration;

import room.Difficulty;

public class Decoration {

    private int id;
    private int roomId;

    private final String name;
    private final String material;
    private final double value;

    public Decoration(String name, String material, double value) {
        this.name = name;
        this.material = material;
        this.value = value;
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

    public String getMaterial() {
        return material;
    }

    public double getValue() {
        return value;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
