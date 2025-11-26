package menu;

import escapeRoom.EscapeRoom;
import escapeRoom.EscapeRoomService;
import input.InputHandler;
import room.Difficulty;
import room.Room;
import room.RoomService;

import java.util.List;

public class EscapeRoomMenuHandler extends EntityMenuHandler<EscapeRoom> {

    private EscapeRoomService service = new EscapeRoomService();
    private RoomService roomService = new RoomService();


    public EscapeRoomMenuHandler(EscapeRoom escapeRoom) {
        super(escapeRoom);
    }

    @Override
    protected int showMenuAndReadOption() {
        System.out.println("We can do the following:");
        System.out.println("1. Edit escape room info");
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
            case 1:
                editEscapeRoom();
                break;
            case 2:
                addRoom();
                break;
            case 3:
                manageRoom();
                break;
            case 4:
                removeRoom();
                break;
            case 5:
                showAllRooms();
                break;
            case 0:
                System.out.println("Going back to main menu...");
                break;
            default:
                System.out.println("Invalid option (" + option + ").");
                break;
        }
    }

    private void editEscapeRoom() {
        String newName = InputHandler.readString("Enter new name for Escape Room");

        try {
            boolean updated = service.updateEscapeRoom(newName);
            System.out.println(updated ? "Escape Room Updated" : "Could not update Escape Room");
            if (updated) {
                entity.setName(newName);
            }
        } catch (Exception error) {
            System.out.println("Error: " + error.getMessage());
        }
    }

    private void addRoom() {
        String name = InputHandler.readString("Enter new Room name: ");

        int diffNumber = InputHandler.readInt("Enter difficulty (1-3): ");

        double price = InputHandler.readDouble("Enter price: ");

        int escapeRoomId = entity.getId();

        try {
            boolean created = roomService.addRoom(name, diffNumber, price, escapeRoomId);
            System.out.println(created ? "Room created" : "Could not create room");
        } catch (Exception error) {
            System.out.println("Error: " + error.getMessage());
        }
    }

    private void manageRoom() {
        int id = InputHandler.readInt("Enter Room ID to manage");
        Room room = roomService.getRoomById(id);

        if (room == null) {
            System.out.println("Room not found");
            return;
        }
        System.out.println("Managing Room: " + room.getName());

        new RoomMenuHandler(room).run();
    }

    private void removeRoom() {

        int id = InputHandler.readInt("Enter Room ID to remove");

        try {
            boolean removed = roomService.removeRoom(id);
            System.out.println(removed ? "Room deleted" : "Could not delete room");
        } catch (Exception error) {
            System.out.println("Error: " + error.getMessage());
        }
    }

    private void showAllRooms() {

        List<Room> rooms = roomService.getRoomsByEscapeRoom(entity.getId());

        if (rooms.isEmpty()) {
            System.out.println("There are no rooms in this escape room.");
            return;
        }

        System.out.println("📌 Rooms in " + entity.getName() + ":");
        for (Room room : rooms) {
            System.out.println("ID: " + room.getId() +
                    " | Name: " + room.getName() +
                    " | Difficulty: " + room.getDifficulty() +
                    " | Price: " + room.getPrice());
        }
    }
}
