package menu;

import Inventory.Inventory;
import Inventory.InventoryService;
import certification.CertificationService;
import decoration.DecorationService;
import escapeRoom.EscapeRoom;
import escapeRoom.EscapeRoomService;
import hint.HintService;
import input.InputHandler;
import reward.RewardService;
import room.RoomService;
import ticket.TicketService;
import user.User;
import user.UserService;

import java.util.List;

public class MainMenuHandler extends AbstractMenuHandler {

    private final EscapeRoomService escapeRoomService;
    private final UserService userService;
    private final InventoryService inventoryService;
    private final RoomService roomService;
    private final HintService hintService;
    private final DecorationService decorationService;
    private final TicketService ticketService;
    private final RewardService rewardService;
    private final CertificationService certificationService;

    public MainMenuHandler(EscapeRoomService escapeRoomService, UserService userService, InventoryService inventoryService, RoomService roomService, HintService hintService, DecorationService decorationService, TicketService ticketService, RewardService rewardService, CertificationService certificationService) {
        this.escapeRoomService = escapeRoomService;
        this.userService = userService;
        this.inventoryService = inventoryService;
        this.roomService = roomService;
        this.hintService = hintService;
        this.decorationService = decorationService;
        this.ticketService = ticketService;
        this.rewardService = rewardService;
        this.certificationService = certificationService;
    }

    @Override
    protected void showMenuOptions() {
        System.out.println("We can do the following:");
        System.out.println("1. Show escape rooms");
        System.out.println("2. Add escape room");
        System.out.println("3. Manage escape room");
        System.out.println("4. Remove escape room");
        System.out.println("5. Show users");
        System.out.println("6. Add user");
        System.out.println("7. Manage user");
        System.out.println("8. Remove user");
        System.out.println("9. Check revenues");
        System.out.println("10. Send notification to subscribed users");
        System.out.println("11. Show full inventory");
        System.out.println("12. Show inventory total value");
        System.out.println("0. Exit");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1 -> showEscapeRooms();
            case 2 -> addEscapeRoom();
            case 3 -> manageEscapeRoom();
            case 4 -> removeEscapeRoom();
            case 5 -> showUsers();
            case 6 -> addUser();
            case 7 -> manageUser();
            case 8 -> removeUser();
            case 9 -> showTotalRevenue();
            case 10 -> sendNotification();
            case 11 -> showFullInventory();
            case 12 -> getInventoryTotalValue();
            case 0 -> System.out.println("See you soon!");
        }
    }

    private void showEscapeRooms() {
        List<EscapeRoom> rooms = escapeRoomService.getEscapeRooms();

        if (rooms.isEmpty()) {
            System.out.println("There ara no escape rooms");
        } else {
            System.out.println("==== Escape Rooms ====");
            rooms.forEach(System.out::println);
        }
    }

    private void addEscapeRoom() {
        String name = InputHandler.readString("Escape Room name");

        try {
            boolean success = escapeRoomService.createEscapeRoom(name);
            System.out.println(success ? "Escape room added successfully" : "Error adding escape room");
        } catch (Exception e) {
            System.out.println("Error adding escape room: " + e.getMessage());
        }
    }

    private void manageEscapeRoom() {
        int id = InputHandler.readInt("Enter escape room Id");

        EscapeRoom escapeRoom = escapeRoomService.getEscapeRoomById(id);

        if (escapeRoom != null) {
            EscapeRoomMenuHandler escapeRoomMenu = new EscapeRoomMenuHandler(escapeRoom, escapeRoomService, roomService, hintService, decorationService, ticketService, certificationService);
            escapeRoomMenu.run();
        } else {
            System.out.println("Escape room not found.");
        }
    }

    private void removeEscapeRoom() {
        int id = InputHandler.readInt("Enter escape room Id");

        try {
            boolean success = escapeRoomService.deleteEscapeRoom(id);
            System.out.println(success ? "Escape room removed successfully" : "Error removing escape room");
        } catch (Exception error) {
            System.out.println("Error removing escape room: " + error.getMessage());
        }
    }

    private void showUsers() {
        List<User> users = userService.getUsers();

        if (users.isEmpty()) {
            System.out.println("There are no users");
        } else {
            System.out.println("==== USERS ====");
            users.forEach(System.out::println);
        }
    }

    private void addUser() {
        System.out.println("==== ADD NEW USER ====");

        String name = InputHandler.readString("Enter first name");
        String surname = InputHandler.readString("Enter surname");
        String email = InputHandler.readString("Enter email");

        try {
            boolean success = userService.addUser(name, surname, email);
            System.out.println(success ? "User added successfully" : "Error adding user");
        } catch (Exception error) {
            System.out.println("Error adding user: " + error.getMessage());
        }
    }

    private void manageUser() {
        int id = InputHandler.readInt("Enter user Id");

        User user = userService.getUserById(id);

        if (user != null) {
            UserMenuHandler userMenu = new UserMenuHandler(user, userService, rewardService, certificationService, ticketService);
            userMenu.run();
        } else {
            System.out.println("User not found");
        }
    }

    private void removeUser() {
        int id = InputHandler.readInt("Enter ID user to delete: ");

        try {
            boolean success = userService.deleteUser(id);
            System.out.println(success ? "User removed successfully" : "Error removing user");
        } catch (Exception error) {
            System.out.println("Error removing user: " + error.getMessage());
        }
    }

    private void showTotalRevenue() {
        double totalRevenue = ticketService.getTicketsTotalRevenue();

        if (totalRevenue > 0) {
            System.out.println("==== TOTAL REVENUE ====");
            System.out.printf("Total income: %.2f €\n", totalRevenue);
        } else {
            System.out.println("No revenues found. There might be no tickets sold yet");
        }
    }

    private void sendNotification() {
        String notification = InputHandler.readString("Enter the notification message: ");
        userService.notifySubscribers(notification);
        System.out.println("Notification sent to all subscribed users.");
    }

    private void showFullInventory() {
        Inventory inventory = inventoryService.getFullInventory();

        System.out.println("==== FULL INVENTORY ====");

        System.out.println("==== Rooms ====");
        if (inventory.rooms().isEmpty()) {
            System.out.println("No rooms available.");
        } else {
            inventory.rooms().forEach(System.out::println);
        }

        System.out.println("==== Decorations ====");
        if (inventory.decorations().isEmpty()) {
            System.out.println("No decorations available.");
        } else {
            inventory.decorations().forEach(System.out::println);
        }

        System.out.println("==== Hints ====");
        if (inventory.hints().isEmpty()) {
            System.out.println("No hints available.");
        } else {
            inventory.hints().forEach(System.out::println);
        }
    }

    private void getInventoryTotalValue() {
        Inventory inventory = inventoryService.getFullInventory();

        double totalInventoryValue = inventory.getInventoryValue();

        System.out.println("==== INVENTORY TOTAL VALUE ====");
        System.out.printf("Total inventory value: %.2f €\n", totalInventoryValue);
    }
}