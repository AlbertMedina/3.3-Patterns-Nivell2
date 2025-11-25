package menu;

import input.InputHandler;

public class UserMenuHandler extends AbstractMenuHandler {

    @Override
    protected int showMenuAndReadOption() {
        System.out.println("We can do the following:");
        System.out.println("1. Edit room info");
        System.out.println("2. Purchase ticket");
        System.out.println("3. Give certification");
        System.out.println("4. Give reward");
        System.out.println("5. Manage subscription");
        System.out.println("0. Back");
        return InputHandler.readInt("Choose what to do next (0-5)");
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
            case 0:
                System.out.println("Going back to main menu...");
                break;
            default:
                System.out.println("Invalid option (" + option + ").");
                break;
        }
    }
}
