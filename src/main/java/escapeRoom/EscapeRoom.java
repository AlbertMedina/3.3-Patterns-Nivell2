package escapeRoom;

import exceptions.ErrorOnIdException;
import exceptions.InvalidNameException;

import java.util.ArrayList;
import java.util.List;



public class EscapeRoom {

    private int id;
    private String name;


    public EscapeRoom(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidNameException();
        }
        this.name = name;
    }

    public EscapeRoom(int id, String name) {
        if (id <= 0) {
            throw new ErrorOnIdException();
        }
        if (name == null || name.isBlank()) {
            throw new InvalidNameException();
        }

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        if (id <= 0) {
            throw new ErrorOnIdException();
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "EscapeRoom{id=" + id + ", name='" + name + "'}";
    }
}




