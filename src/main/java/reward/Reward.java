package reward;

import java.time.LocalDateTime;

public class Reward {

    private int id;
    private String name;
    private String description;
    private LocalDateTime date;
    private int userId;


    public Reward(int id, String name, String description, LocalDateTime date, int userId) {
        this.id = id;

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        this.name = name;

        if (description == null || description.isEmpty())
            throw new IllegalArgumentException("Description cannot be null or empty");
        this.description = description;

        if (date == null)
            throw new IllegalArgumentException("Date cannot be null");
        this.date = date;

        this.userId = userId;
    }

    public Reward(String name, String description, LocalDateTime date, int userId) {
        this(0, name, description, date, userId);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getUserId() {
        return userId;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        this.name = name;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty())
            throw new IllegalArgumentException("Description cannot be null or empty");
        this.description = description;
    }

    public void setDate(LocalDateTime date) {
        if (date == null)
            throw new IllegalArgumentException("Date cannot be null");
        this.date = date;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Reward{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", description = '" + description + '\'' +
                ", date = " + date +
                ", userId = " + userId +
                '}';
    }
}
