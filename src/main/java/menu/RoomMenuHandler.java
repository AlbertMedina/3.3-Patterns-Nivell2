package menu;

import decoration.Decoration;
import decoration.DecorationService;
import hint.Hint;
import hint.HintService;
import input.InputHandler;
import room.Difficulty;
import room.Room;
import room.RoomService;

public class RoomMenuHandler extends EntityMenuHandler<Room> {

    RoomService roomService;
    HintService hintService;
    DecorationService decorationService;

    public RoomMenuHandler(Room room) {
        super(room);
        roomService = new RoomService();
        hintService = new HintService();
        decorationService = new DecorationService();
    }

    @Override
    protected int showMenuAndReadOption() {
        System.out.println("ROOM ID:" + entity.getId() + " (" + entity.getName() + ")" + " MENU");
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
                manageHint();
                break;
            case 4:
                removeHint();
                break;
            case 5:
                addDecoration();
                break;
            case 6:
                manageDecoration();
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
        System.out.println("Editing data for room Id:" + entity.getId());

        String newName = InputHandler.readString("Enter new name (current: " + entity.getName() + ")");
        int newDifficultyValue = InputHandler.readInt("Enter new difficulty (1-3) (current: " + entity.getDifficulty().toString().toLowerCase() + ")");
        double newPrice = InputHandler.readDouble("Enter new price (current: " + entity.getPrice() + ")");
        int newEscapeRoomId = InputHandler.readInt("Enter new escape room id (current: " + entity.getEscapeRoomId() + ")");

        try {
            boolean success = roomService.updateRoom(entity.getId(), newName, newDifficultyValue, newPrice, newEscapeRoomId);
            if (success) {
                entity.setName(newName);
                entity.setDifficulty(newDifficultyValue);
                entity.setPrice(newPrice);
                entity.setEscapeRoomId(newEscapeRoomId);
                System.out.println("Data updated successfully for room Id:" + entity.getId());
            } else {
                System.out.println("Error updating data for room Id:" + entity.getId());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating data for room Id:" + entity.getId() + ": " + e.getMessage());
        }
    }

    private void addHint() {
        System.out.println("Adding new hint to room Id:" + entity.getId());

        String text = InputHandler.readString("Enter hint text");
        String theme = InputHandler.readString("Enter hint theme");
        Double value = InputHandler.readDouble("Enter hint value");

        try {
            boolean success = hintService.addHint(text, theme, value, entity.getId());
            if (success) {
                System.out.println("Hint added successfully to room Id:" + entity.getId());
            } else {
                System.out.println("Error adding hint to room Id:" + entity.getId());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding hint to room Id:" + entity.getId() + ": " + e.getMessage());
        }
    }

    private void manageHint() {
        int hintId = InputHandler.readInt("Enter hint Id");

        Hint hint = hintService.getHintById(hintId);

        if (hint != null) {
            HintMenuHandler hintMenu = new HintMenuHandler(hint);
            hintMenu.run();
        } else {
            System.out.println("Hint Id:" + hintId + " could not be found");
        }
    }

    private void removeHint() {
        System.out.println("Removing hint from room Id:" + entity.getId());

        int hintId = InputHandler.readInt("Enter hint Id");

        try {
            boolean success = hintService.removeHint(hintId);
            if (success) {
                System.out.println("Hint removed successfully from room Id:" + entity.getId());
            } else {
                System.out.println("Error removing hint from room Id:" + entity.getId());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error removing hint from room Id:" + entity.getId() + ": " + e.getMessage());
        }
    }

    private void addDecoration() {
        System.out.println("Adding new decoration to room Id:" + entity.getId());

        String name = InputHandler.readString("Enter decoration name");
        String material = InputHandler.readString("Enter decoration material");
        Double value = InputHandler.readDouble("Enter decoration value");

        try {
            boolean success = decorationService.addDecoration(name, material, value, entity.getId());
            if (success) {
                System.out.println("Decoration added successfully to room Id:" + entity.getId());
            } else {
                System.out.println("Error adding decoration to room Id:" + entity.getId());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding decoration to room Id:" + entity.getId() + ": " + e.getMessage());
        }
    }

    private void manageDecoration() {
        int decorationId = InputHandler.readInt("Enter decoration Id");

        Decoration decoration = decorationService.getDecorationById(decorationId);
        if (decoration != null) {
            DecorationMenuHandler decorationMenu = new DecorationMenuHandler(decoration);
            decorationMenu.run();
        } else {
            System.out.println("Decoration Id:" + decorationId + " could not be found");
        }
    }

    private void removeDecoration() {
        System.out.println("Removing decoration from room Id:" + entity.getId());

        int decorationId = InputHandler.readInt("Enter decoration Id");

        try {
            boolean success = decorationService.removeDecoration(decorationId);
            if (success) {
                System.out.println("Decoration removed successfully from room Id:" + entity.getId());
            } else {
                System.out.println("Error removing hint from room Id:" + entity.getId());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error removing hint from room Id:" + entity.getId() + ": " + e.getMessage());
        }
    }
}
