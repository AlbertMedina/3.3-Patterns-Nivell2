package certification;

import java.time.LocalDate;

public class Certification {

    private int id;
    private String name;
    private LocalDate date;
    private int roomId;
    private int userId;

    public Certification(String name, LocalDate date, int roomId, int userId) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }

        if (date == null) {
            throw new IllegalArgumentException("Invalid date");
        }

        this.name = name;
        this.date = date;
        this.roomId = roomId;
        this.userId = userId;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.date = date;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Certification ID" + id + " -> Name: " + name + " | Date: " + date + " Room ID: " + roomId + " | User id: " + userId;
    }
}
