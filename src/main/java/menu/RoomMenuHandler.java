package menu;

import input.InputHandler;

public class RoomMenuHandler extends AbstractMenuHandler {

    @Override
    protected int showMenuAndReadOption() {
        System.out.println("We can do the following:");
        System.out.println("1. Edit room info");
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
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 0:
                System.out.println("Going back to escape room menu...");
                break;
            default:
                System.out.println("Invalid option (" + option + ").");
                break;
        }
    }
}
