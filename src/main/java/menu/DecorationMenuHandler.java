package menu;

import decoration.Decoration;
import decoration.DecorationService;
import input.InputHandler;

public class DecorationMenuHandler extends EntityMenuHandler<Decoration> {

    DecorationService decorationService;

    public DecorationMenuHandler(Decoration decoration) {
        super(decoration);
        decorationService = new DecorationService();
    }

    @Override
    protected int showMenuAndReadOption() {
        System.out.println("DECORATION ID:" + entity.getId() + " MENU");
        System.out.println("We can do the following:");
        System.out.println("1. Edit decoration data");
        System.out.println("0. Back");
        return InputHandler.readInt("Choose what to do next (0-1)");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1:
                editDecorationData();
                break;
            case 0:
                System.out.println("Going back to room menu...");
                break;
            default:
                System.out.println("Invalid option (" + option + ").");
                break;
        }
    }

    private void editDecorationData() {
        System.out.println("Editing data for decoration Id:" + entity.getId());
        String name = InputHandler.readString("Enter new name (current: " + entity.getName() + ")");
        String material = InputHandler.readString("Enter new material (current: " + entity.getMaterial() + ")");
        double value = InputHandler.readDouble("Enter new value (current: " + entity.getValue() + ")");
        int roomId = InputHandler.readInt("Enter new room id (current: " + entity.getRoomId() + ")");

        try {
            boolean success = decorationService.updateDecoration(name, material, value, roomId);
            if (success) {
                System.out.println("Data updated successfully for decoration Id:" + entity.getId());
            } else {
                System.out.println("Error updating data for decoration Id:" + entity.getId());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating data for decoration Id:" + entity.getId() + ": " + e.getMessage());
        }
    }
}
