package menu;

import certification.CertificationService;
import input.InputHandler;
import reward.RewardService;
import ticket.TicketService;
import user.User;

import java.time.LocalDate;

public class UserMenuHandler extends EntityMenuHandler<User> {

    public UserMenuHandler(User user) {
        super(user);
    }

    private RewardService rewardService = new RewardService();
    private CertificationService certificationService = new CertificationService();
    private TicketService ticketService = new TicketService();

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
            case 2: purchaseTicket();
                break;
            case 3: giveCertification();
                break;
            case 4: giveReward();
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

    private void giveReward() {
        System.out.println("==== GIVE REWARD ====");
        String name = InputHandler.readString("Reward name: ");
        String description = InputHandler.readString("Reward description: ");
        int userId = InputHandler.readInt("User ID: ");

        boolean created = rewardService.addReward(name, description, LocalDate.now(), userId);

        if (created) {
            System.out.println("Reward created successfully!");
        } else {
            System.out.println("Error: reward could not be created.");
        }
    }

    private void giveCertification() {
        System.out.println("==== GIVE CERTIFICATION ====");
        String name = InputHandler.readString("Certification name:  ");
        int roomID = InputHandler.readInt("Room ID: ");
        int userID = InputHandler.readInt("User ID:  ");

        boolean created = certificationService.addCertification(name, LocalDate.now(), roomID, userID);

        if (created) {
            System.out.println("Certification added successfully!");
        } else {
            System.out.println("Error: certification could not be created.");
        }
    }

    private void purchaseTicket() {
        System.out.println("==== PURCHASE TICKET =====");

        int roomId = InputHandler.readInt("Room ID: ");
        int userId = InputHandler.readInt("User ID: ");
        double price = InputHandler.readDouble("Ticket price: ");

        boolean created = ticketService.addTicket(LocalDate.now(), price, roomId, userId);

        if (created) {
            System.out.println("Ticket purchased successfully!");
        } else {
            System.out.println("Error: ticket could not be purchased.");
        }
    }
}


