package reward;

import exceptions.ParameterValidationException;

import java.time.LocalDate;

public class Reward {

    private int id;
    private String name;
    private String description;
    private LocalDate date;
    private int userId;


    public Reward(String name, String description, LocalDate date, int userId) {

        if (name == null || name.trim().isEmpty()) {
            throw new ParameterValidationException("Name cannot be null or empty");
        }

        if (description == null || description.trim().isEmpty()) {
            throw new ParameterValidationException("Description cannot be null or empty");
        }

        if (date == null) {
            throw new ParameterValidationException("Date cannot be null");
        }

        this.name = name;
        this.description = description;
        this.date = date;
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
            throw new ParameterValidationException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new ParameterValidationException("Description cannot be null or empty");
        }
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date == null) {
            throw new ParameterValidationException("Date cannot be null");
        }
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Reward ID" + id + " -> Name: " + name + " | Description: " + description + " | Date: " + date + " | User ID: " + userId;
    }
}
