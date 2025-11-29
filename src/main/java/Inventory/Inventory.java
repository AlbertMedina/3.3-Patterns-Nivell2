package Inventory;

import decoration.Decoration;
import hint.Hint;
import room.Room;

import java.util.List;

public record Inventory(List<Room> rooms, List<Decoration> decorations, List<Hint> hints) {

    public double getInventoryValue() {
        double roomsValue = rooms.stream().mapToDouble(Room::getPrice).sum();
        double decorationsValue = decorations.stream().mapToDouble(Decoration::getValue).sum();
        double hintsValue = hints.stream().mapToDouble(Hint::getValue).sum();
        return roomsValue + decorationsValue + hintsValue;
    }
}
