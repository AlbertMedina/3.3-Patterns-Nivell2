package escapeRoom;

import exceptions.ParameterValidationException;

public class EscapeRoom {

    private int id;
    private String name;

    public EscapeRoom(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ParameterValidationException("Invalid name");
        }

        this.name = name;
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
            throw new ParameterValidationException("Invalid name");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "EscapeRoom ID" + id + " -> Name: " + name;
    }
}
