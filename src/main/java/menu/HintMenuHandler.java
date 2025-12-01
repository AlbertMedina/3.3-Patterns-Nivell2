package menu;

import hint.Hint;
import hint.HintService;
import input.InputHandler;

public class HintMenuHandler extends EntityMenuHandler<Hint> {

    private final HintService hintService;

    public HintMenuHandler(Hint hint, HintService hintService) {
        super(hint);
        this.hintService = hintService;
    }

    @Override
    protected void showMenuOptions() {
        System.out.println("HINT ID:" + entity.getId() + " MENU");
        System.out.println("We can do the following:");
        System.out.println("1. Edit hint data");
        System.out.println("0. Back");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1 -> editHintData();
            case 0 -> System.out.println("Going back to room menu...");
            default -> System.out.println("Invalid option (" + option + ").");
        }
    }

    private void editHintData() {
        System.out.println("Editing data for hint Id:" + entity.getId());

        String newText = InputHandler.readString("Enter new text (current: " + entity.getText() + ")");
        String newTheme = InputHandler.readString("Enter new theme (current: " + entity.getTheme() + ")");
        double newValue = InputHandler.readDouble("Enter new value (current: " + entity.getValue() + ")");
        int newRoomId = InputHandler.readInt("Enter new room id (current: " + entity.getRoomId() + ")");

        try {
            boolean success = hintService.updateHint(entity.getId(), newText, newTheme, newValue, newRoomId);
            if (success) {
                entity.setText(newText);
                entity.setTheme(newTheme);
                entity.setValue(newValue);
                entity.setRoomId(newRoomId);
                System.out.println("Data updated successfully for hint Id:" + entity.getId());
            } else {
                System.out.println("Error updating data for hint Id:" + entity.getId());
            }
        } catch (Exception e) {
            System.out.println("Error updating data for hint Id:" + entity.getId() + ": " + e.getMessage());
        }
    }
}