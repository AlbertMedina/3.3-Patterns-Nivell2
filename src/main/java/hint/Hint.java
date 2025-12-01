package hint;

public class Hint {

    private int id;
    private String text;
    private String theme;
    private double value;
    private int roomId;

    public Hint(String text, String theme, double value, int roomId) {

        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Text can not be null or empty");
        }

        if (theme == null || theme.trim().isEmpty()) {
            throw new IllegalArgumentException("Theme can not be null or empty");
        }

        if (value <= 0) {
            throw new IllegalArgumentException("Value can not be negative");
        }

        this.text = text;
        this.theme = theme;
        this.value = value;
        this.roomId = roomId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Text can not be null or empty");
        }
        this.text = text;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        if (theme == null || theme.trim().isEmpty()) {
            throw new IllegalArgumentException("Theme can not be null or empty");
        }
        this.theme = theme;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Value can not be negative");
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
        return "Hint ID" + id + " -> Text: " + text + " | Theme: " + theme + " | Value: " + value + "€ | Room ID: " + roomId;
    }
}

