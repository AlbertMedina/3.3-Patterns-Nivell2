package menu;

import escapeRoom.EscapeRoom;
import escapeRoom.EscapeRoomService;
import input.InputHandler;

public class EscapeRoomMenuHandler extends AbstractMenuHandler {

    private EscapeRoomService service = new EscapeRoomService();

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
                addEscapeRoom();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
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
        int id = InputHandler.readInt("Enter Escape Room ID to edit");
        EscapeRoom escapeRoom = service.getEscapeRoom(id);
        if (escapeRoom == null) {
            System.out.println("️Escape Room not found");
            return;
        }

        String newName = InputHandler.readString("Enter new name for Escape Room");

        try {
            boolean updated = service.updateEscapeRoom(id, newName);
            System.out.println(updated ? "Escape Room Updated" : " Could not update Escape Room");
        } catch (Exception error) {
            System.out.println("Error: " + error.getMessage());
        }
    }

    private void addEscapeRoom() {
        String name = InputHandler.readString("Enter new Escape Room name: ");
        try {
            boolean created = service.createEscapeRoom(name);
            System.out.println(created ? "Escape Room created" : " Could not create Escape Room");
        } catch (Exception error) {
            System.out.println("Error: " + error.getMessage());
        }
    }
}
