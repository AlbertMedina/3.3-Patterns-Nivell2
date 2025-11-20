package decoration;

public class Decoration {

    private int id;
    private String name;
    private String material;
    private double value;
    private int roomId;

    public Decoration(String name, String material, double value, int roomId) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }

        if (material == null) {
            throw new IllegalArgumentException("Invalid material");
        }

        if (value <= 0) {
            throw new IllegalArgumentException("Invalid value");
        }

        this.name = name;
        this.material = material;
        this.value = value;
        this.roomId = roomId;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if (material == null) {
            throw new IllegalArgumentException("Invalid material");
        }
        this.material = material;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Decoration { id: " + id + ", name: " + name + ", material: " + material + ", value: " + value + ", room id: " + roomId + " }";
    }
}
