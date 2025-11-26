package menu;

import escapeRoom.EscapeRoom;
import escapeRoom.EscapeRoomService;
import input.InputHandler;

import java.util.List;

public class MainMenuHandler extends AbstractMenuHandler {

    private EscapeRoomService escapeRoomService = new EscapeRoomService();


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
                addEscapeRoom();
                break;
            case 2:
                manageEscapeRoom();
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

    private void addEscapeRoom() {
        String name = InputHandler.readString("Escape Room name: ");
        boolean create = escapeRoomService.createEscapeRoom(name);

        System.out.println(create ? "Escape Room created!" : "Could not create Escape Room");
    }

    private void manageEscapeRoom() {

        List<EscapeRoom> rooms = escapeRoomService.listEscapeRooms();
        if (rooms.isEmpty()) {
            System.out.println("No Escape Rooms available");
            return;
        }

        System.out.println("==== Escape Rooms ====");
        for (EscapeRoom r : rooms) {
            System.out.println(r.getId() + " - " + r.getName());
        }

        int id = InputHandler.readInt("Enter ID to manage: ");
        EscapeRoom select = escapeRoomService.getEscapeRoom(id);

        if (select == null) {
            System.out.println("Escape room not found.");
            return;
        }
        new EscapeRoomMenuHandler(select).run();
    }

}
