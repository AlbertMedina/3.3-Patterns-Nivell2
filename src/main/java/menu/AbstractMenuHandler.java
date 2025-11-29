package menu;

import input.InputHandler;

public abstract class AbstractMenuHandler {

    public void run() {
        int option;
        do {
            showMenuOptions();
            option = InputHandler.readInt("Choose what to do next");
            handleOption(option);
        } while (option != 0);
    }

    protected abstract void showMenuOptions();

    protected abstract void handleOption(int option);
}
