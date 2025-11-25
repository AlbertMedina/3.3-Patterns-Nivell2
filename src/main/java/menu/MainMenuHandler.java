package menu;

import input.InputHandler;

public class MainMenuHandler extends AbstractMenuHandler {

    @Override
    protected int showMenuAndReadOption() {
        System.out.println("We can do the following:");
        System.out.println("1. Add escape room");
        System.out.println("2. Manage escape room");
        System.out.println("3. Remove escape room");
        System.out.println("4. Add user");
        System.out.println("5. Manage user");
        System.out.println("6. Remove user");
        System.out.println("7. Check revenues");
        System.out.println("8. Send notification to subscribed users");
        System.out.println("0. Exit");
        return InputHandler.readInt("Choose what to do next (0-8)");
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
            case 8:
                break;
            case 0:
                System.out.println("See you soon!");
                break;
            default:
                System.out.println("Invalid option (" + option + ").");
                break;
        }
    }
}
