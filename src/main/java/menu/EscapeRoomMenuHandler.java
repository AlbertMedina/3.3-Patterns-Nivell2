package menu;

import escapeRoom.EscapeRoom;
import input.InputHandler;

public class EscapeRoomMenuHandler extends EntityMenuHandler<EscapeRoom> {

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
        System.out.println("0. Back");
        return InputHandler.readInt("Choose what to do next (0-4)");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 0:
                System.out.println("Going back to main menu...");
                break;
            default:
                System.out.println("Invalid option (" + option + ").");
                break;
        }
    }
}
