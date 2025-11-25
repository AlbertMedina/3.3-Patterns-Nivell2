package menu;

import hint.Hint;
import hint.HintService;
import input.InputHandler;

public class HintMenuHandler extends EntityMenuHandler<Hint> {

    HintService hintService;

    public HintMenuHandler(Hint hint) {
        super(hint);
        hintService = new HintService();
    }

    @Override
    protected int showMenuAndReadOption() {
        System.out.println("HINT ID:" + entity.getId() + " MENU");
        System.out.println("We can do the following:");
        System.out.println("1. Edit hint data");
        System.out.println("0. Back");
        return InputHandler.readInt("Choose what to do next (0-1)");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1:
                editHintData();
                break;
            case 0:
                System.out.println("Going back to room menu...");
                break;
            default:
                System.out.println("Invalid option (" + option + ").");
                break;
        }
    }

    private void editHintData() {
        System.out.println("Editing data for hint Id:" + entity.getId());
        String text = InputHandler.readString("Enter new text (current: " + entity.getText() + ")");
        String theme = InputHandler.readString("Enter new theme (current: " + entity.getTheme() + ")");
        double value = InputHandler.readDouble("Enter new value (current: " + entity.getValue() + ")");
        int roomId = InputHandler.readInt("Enter new room id (current: " + entity.getRoomId() + ")");

        try {
            boolean success = hintService.updateHint(text, theme, value, roomId);
            if (success) {
                System.out.println("Data updated successfully for hint Id:" + entity.getId());
            } else {
                System.out.println("Error updating data for hint Id:" + entity.getId());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating data for hint Id:" + entity.getId() + ": " + e.getMessage());
        }
    }
}