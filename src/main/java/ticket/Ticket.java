package ticket;

import exceptions.ParameterValidationException;

import java.time.LocalDate;

public class Ticket {

    private int id;
    private LocalDate date;
    private double price;
    private int roomId;
    private int userId;

    public Ticket(LocalDate date, double price, int roomId, int userId) {

        if (date == null) {
            throw new ParameterValidationException("Invalid date");
        }

        if (price <= 0) {
            throw new ParameterValidationException("Invalid price");
        }

        this.date = date;
        this.price = price;
        this.roomId = roomId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        if (date == null) {
            throw new ParameterValidationException("Invalid date");
        }
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new ParameterValidationException("Invalid price");
        }
        this.price = price;
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
        return "Ticket ID" + id + " -> Date: " + date + " | Price: " + price + "€ | Room ID: " + roomId + " | User ID: " + userId;
    }
}
