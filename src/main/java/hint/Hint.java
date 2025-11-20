package hint;

public class Hint {

    private int id;
    private String text;
    private String theme;
    private double value;
    private int roomId;

    public Hint(String text, String theme, double value, int roomId) {

        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text can not be null or empty");
        }
        this.text = text;
        if (theme == null || theme.isEmpty()) {
            throw new IllegalArgumentException("Theme can not be null or empty");
        }
        this.theme = theme;
        if (value < 0) {
            throw new IllegalArgumentException("Value can not be negative");
        }
        this.value = value;
        this.roomId = roomId;
    }


    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTheme() {
        return theme;
    }

    public double getValue() {
        return value;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Hint{" +
                "id = " + id +
                ", text = '" + text + '\'' +
                ", theme = '" + theme + '\'' +
                ", value = " + value +
                ", roomId = " + roomId +
                '}';
    }
}

