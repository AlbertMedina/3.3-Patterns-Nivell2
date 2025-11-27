package menu;

import escapeRoom.EscapeRoom;
import escapeRoom.EscapeRoomService;
import input.InputHandler;
import room.Room;
import room.RoomService;

import java.util.List;

public class EscapeRoomMenuHandler extends EntityMenuHandler<EscapeRoom> {

    private final EscapeRoomService escapeRoomService;
    private final RoomService roomService;

    public EscapeRoomMenuHandler(EscapeRoom escapeRoom) {
        super(escapeRoom);
        escapeRoomService = new EscapeRoomService();
        roomService = new RoomService();
    }

    @Override
    protected int showMenuAndReadOption() {
        System.out.println("We can do the following:");
        System.out.println("1. Edit escape room data");
        System.out.println("2. Add room");
        System.out.println("3. Manage room");
        System.out.println("4. Remove room");
        System.out.println("5. Show all Rooms");
        System.out.println("0. Back");
        return InputHandler.readInt("Choose what to do next (0-5)");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1 -> editEscapeRoomData();
            case 2 -> addRoom();
            case 3 -> manageRoom();
            case 4 -> removeRoom();
            case 5 -> showAllRooms();
            case 0 -> System.out.println("Going back to main menu...");
            default -> System.out.println("Invalid option (" + option + ").");
        }
    }

    private void editEscapeRoomData() {
        System.out.println("Editing data for escape room Id:" + entity.getId());

        String newName = InputHandler.readString("Enter new name (current: " + entity.getName() + ")");

        try {
            boolean success = escapeRoomService.updateEscapeRoom(entity.getId(), newName);
            System.out.println(success ? "Escape Room Updated" : "Could not update Escape Room");
            if (success) {
                entity.setName(newName);
                System.out.println("Data updated successfully for escape room Id:" + entity.getId());
            } else {
                System.out.println("Error updating data for escape room Id:" + entity.getId());
            }
        } catch (Exception e) {
            System.out.println("Error updating data for escape room Id:" + entity.getId() + ": " + e.getMessage());
        }
    }

    private void addRoom() {
        System.out.println("Adding new room to escape room Id:" + entity.getId());

        String name = InputHandler.readString("Enter room name: ");
        int difficultyValue = InputHandler.readInt("Enter room difficulty (1-3): ");
        double price = InputHandler.readDouble("Enter room price: ");

        try {
            boolean success = roomService.addRoom(name, difficultyValue, price, entity.getId());
            if (success) {
                System.out.println("Room added successfully to escape room Id:" + entity.getId());
            } else {
                System.out.println("Error adding room to escape room Id:" + entity.getId());
            }
        } catch (Exception e) {
            System.out.println("Error adding room to escape room Id:" + entity.getId() + ": " + e.getMessage());
        }
    }

    private void manageRoom() {
        int roomId = InputHandler.readInt("Enter room Id");

        Room room = roomService.getRoomById(roomId);

        if (room != null) {
            RoomMenuHandler roomMenu = new RoomMenuHandler(room);
            roomMenu.run();
        } else {
            System.out.println("Room Id:" + roomId + " could not be found");
        }
    }

    private void removeRoom() {
        System.out.println("Removing room from escape room Id:" + entity.getId());

        int roomId = InputHandler.readInt("Enter room Id");

        try {
            boolean success = roomService.removeRoom(roomId);
            if (success) {
                System.out.println("Room removed successfully from escape room Id:" + entity.getId());
            } else {
                System.out.println("Error removing room from escape room Id:" + entity.getId());
            }
        } catch (Exception e) {
            System.out.println("Error removing room from escape room Id:" + entity.getId() + ": " + e.getMessage());
        }
    }

    private void showAllRooms() {
        List<Room> rooms = roomService.getRoomsByEscapeRoom(entity.getId());

        if (rooms.isEmpty()) {
            System.out.println("There are no rooms in this escape room");
        } else {
            System.out.println("📌 Rooms in escape room Id:" + entity.getId() + " (" + entity.getName() + "):");
            rooms.forEach(System.out::println);
        }
    }
}
