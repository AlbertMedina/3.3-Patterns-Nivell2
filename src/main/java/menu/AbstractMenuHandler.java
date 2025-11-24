package menu;

public abstract class AbstractMenuHandler {

    public void run() {
        int option;
        do {
            option = showMenuAndReadOption();
            handleOption(option);
        } while (option != 0);
    }
    
    protected abstract int showMenuAndReadOption();

    protected abstract void handleOption(int option);
}
