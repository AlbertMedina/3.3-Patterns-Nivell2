package menu;

import escapeRoom.EscapeRoom;
import escapeRoom.EscapeRoomService;
import input.InputHandler;
import user.User;
import user.UserService;

import java.util.List;

public class MainMenuHandler extends AbstractMenuHandler {

    private EscapeRoomService escapeRoomService = new EscapeRoomService();
    private UserService userService = new UserService();


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
                deleteEscapeRoom();
                break;
            case 4:
                addUser();
                break;
            case 5:
                manageUsers();
                break;
            case 6:
                removeUser();
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

    private void deleteEscapeRoom() {
        int id = InputHandler.readInt("Enter ID to delete: ");

        try {
            boolean ok = escapeRoomService.deleteEscapeRoom(id);
            System.out.println(ok ? "Escape Room removed!" : "Could not delete Escape Room");

        } catch (Exception error) {
            System.out.println("Error deleting Escape Room: " + error.getMessage());
        }
    }

    private void addUser() {
        System.out.println("==== ADD NEW USER ====");

        String name = InputHandler.readString("Enter first name: ");
        String surname = InputHandler.readString("Enter surname: ");
        String email = InputHandler.readString("Enter email: ");

        try {
            boolean create = userService.addUser(name, surname, email);
            System.out.println(create ? "User created succesfully!" : "Could not create user");

        } catch (Exception error) {
            System.out.println("Error creating user: " + error.getMessage());
        }
    }

    private void manageUsers() {
        List<User> users = userService.getUsers();
        if (users.isEmpty()) {
            System.out.println("No users available");
            return;
        }

        System.out.println("==== USERS ====");
        for (User user : users) {
            System.out.println(user.getId() + " - " + user.getName() + " " + user.getSurnames());
        }

        int id = InputHandler.readInt("Enter ID user to manage: ");
        User selected = userService.getUserById(id);

        if (selected == null) {
            System.out.println("User not found.");
            return;
        }
        new UserMenuHandler(selected).run();
    }

    private void removeUser() {
        int id = InputHandler.readInt("Enter ID user to delete: ");

        try {
            boolean ok = userService.deleteUser(id);
            System.out.println(ok ? "User removed!" : "Could not delete user");
        } catch (Exception error) {
            System.out.println("Error deleting user: " + error.getMessage());
        }
    }
}
