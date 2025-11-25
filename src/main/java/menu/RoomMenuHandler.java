package menu;

import decoration.DecorationService;
import hint.HintService;
import input.InputHandler;
import room.Difficulty;
import room.Room;
import room.RoomService;

public class RoomMenuHandler extends AbstractMenuHandler {

    private Room room;

    RoomService roomService;
    HintService hintService;
    DecorationService decorationService;

    public RoomMenuHandler() {
        roomService = new RoomService();
        hintService = new HintService();
        decorationService = new DecorationService();
    }

    @Override
    protected int showMenuAndReadOption() {
        System.out.println("We can do the following:");
        System.out.println("1. Edit room data");
        System.out.println("2. Add hint");
        System.out.println("3. Manage hint");
        System.out.println("4. Remove hint");
        System.out.println("5. Add decoration");
        System.out.println("6. Manage decoration");
        System.out.println("7. Remove decoration");
        System.out.println("0. Back");
        return InputHandler.readInt("Choose what to do next (0-7)");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1:
                editRoomData();
                break;
            case 2:
                addHint();
                break;
            case 3:
                break;
            case 4:
                removeHint();
                break;
            case 5:
                addDecoration();
                break;
            case 6:
                break;
            case 7:
                removeDecoration();
                break;
            case 0:
                System.out.println("Going back to escape room menu...");
                break;
            default:
                System.out.println("Invalid option (" + option + ").");
                break;
        }
    }

    private void editRoomData() {
        System.out.println("Editing data for room Id:" + room.getId());
        String name = InputHandler.readString("Enter new name (current: " + room.getName() + ")");
        int difficulty = InputHandler.readInt("Enter new difficulty (1-3) (current: " + room.getDifficulty().toString().toLowerCase() + ")");
        Double price = InputHandler.readDouble("Enter new price (current: " + room.getPrice() + ")");
        int escapeRoomId = InputHandler.readInt("Enter new escape room id (current: " + room.getEscapeRoomId() + ")");

        boolean success = roomService.updateRoom(name, Difficulty.fromInt(difficulty), price, escapeRoomId);
        if (success) {
            System.out.println("Data updated successfully for room Id:" + room.getId());
        } else {
            System.out.println("Error updating data for room Id:" + room.getId());
        }
    }

    private void addHint() {
        System.out.println("Adding new hint to room Id:" + room.getId());
        String text = InputHandler.readString("Enter hint text");
        String theme = InputHandler.readString("Enter hint theme");
        Double value = InputHandler.readDouble("Enter hint value");

        boolean success = hintService.addHint(text, theme, value, room.getId());
        if (success) {
            System.out.println("Hint added successfully to room Id:" + room.getId());
        } else {
            System.out.println("Error adding hint to room Id:" + room.getId());
        }
    }

    private void removeHint() {
        System.out.println("Removing hint from room Id:" + room.getId());
        int hintId = InputHandler.readInt("Enter hint Id");

        boolean success = hintService.removeHint(hintId);
        if (success) {
            System.out.println("Hint removed successfully from room Id:" + room.getId());
        } else {
            System.out.println("Error removing hint from room Id:" + room.getId());
        }
    }

    private void addDecoration() {
        System.out.println("Adding new decoration to room Id:" + room.getId());
        String name = InputHandler.readString("Enter decoration name");
        String material = InputHandler.readString("Enter decoration material");
        Double value = InputHandler.readDouble("Enter decoration value");

        boolean success = decorationService.addDecoration(name, material, value, room.getId());
        if (success) {
            System.out.println("Decoration added successfully to room Id:" + room.getId());
        } else {
            System.out.println("Error adding decoration to room Id:" + room.getId());
        }
    }

    private void removeDecoration() {
        System.out.println("Removing decoration from room Id:" + room.getId());
        int decorationId = InputHandler.readInt("Enter decoration Id");

        boolean success = decorationService.removeDecoration(decorationId);
        if (success) {
            System.out.println("Decoration removed successfully from room Id:" + room.getId());
        } else {
            System.out.println("Error removing hint from room Id:" + room.getId());
        }
    }
}
