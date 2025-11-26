package menu;

import certification.CertificationService;
import input.InputHandler;
import reward.RewardService;
import ticket.TicketService;
import user.User;
import user.UserService;

import java.time.LocalDate;

public class UserMenuHandler extends EntityMenuHandler<User> {

    private final UserService userService;
    private final RewardService rewardService;
    private final CertificationService certificationService;
    private final TicketService ticketService;

    public UserMenuHandler(User user) {
        super(user);
        userService = new UserService();
        rewardService = new RewardService();
        certificationService = new CertificationService();
        ticketService = new TicketService();
    }

    @Override
    protected int showMenuAndReadOption() {
        System.out.println("USER ID:" + entity.getId() + " (" + entity.getName() + " " + entity.getSurnames() + ")" + " MENU");
        System.out.println("We can do the following:");
        System.out.println("1. Edit user data");
        System.out.println("2. " + (entity.isSubscribed() ? "Cancel subscription" : "Subscribe"));
        System.out.println("3. Purchase ticket");
        System.out.println("4. Give certification");
        System.out.println("5. Give reward");
        System.out.println("0. Back");
        return InputHandler.readInt("Choose what to do next (0-5)");
    }

    @Override
    protected void handleOption(int option) {
        switch (option) {
            case 1:
                editUserData();
                break;
            case 2:
                updateSubscription(!entity.isSubscribed());
                break;
            case 3:
                purchaseTicket();
                break;
            case 4:
                giveCertification();
                break;
            case 5:
                giveReward();
                break;
            case 0:
                System.out.println("Going back to main menu...");
                break;
            default:
                System.out.println("Invalid option (" + option + ").");
                break;
        }
    }

    private void editUserData() {
        System.out.println("Editing data for user Id:" + entity.getId());

        String newName = InputHandler.readString("Enter new name (current: " + entity.getName() + ")");
        String newSurnames = InputHandler.readString("Enter new surnames (current: " + entity.getSurnames() + ")");
        String newEmail = InputHandler.readString("Enter new email (current: " + entity.getEmail() + ")");

        try {
            boolean success = userService.updateUser(entity.getId(), newName, newSurnames, newEmail);
            if (success) {
                entity.setName(newName);
                entity.setSurnames(newSurnames);
                entity.setEmail(newEmail);
                System.out.println("Data updated successfully for user Id:" + entity.getId());
            } else {
                System.out.println("Error updating data for user Id:" + entity.getId());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating data for user Id:" + entity.getId() + ": " + e.getMessage());
        }
    }

    private void giveReward() {
        System.out.println("Giving a new reward to user Id:" + entity.getId());

        String name = InputHandler.readString("Enter reward name");
        String description = InputHandler.readString("Enter reward description");

        try {
            boolean success = rewardService.addReward(name, description, LocalDate.now(), entity.getId());
            if (success) {
                System.out.println("Reward given successfully to user Id:" + entity.getId());
            } else {
                System.out.println("Error giving reward to user Id:" + entity.getId());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error giving reward to user Id:" + entity.getId() + ": " + e.getMessage());
        }
    }

    private void giveCertification() {
        System.out.println("Giving a new certification to user Id:" + entity.getId() + " for completing a room");

        String name = InputHandler.readString("Enter certification name");
        int roomId = InputHandler.readInt("Enter the Id of the completed room");

        try {
            boolean success = certificationService.addCertification(name, LocalDate.now(), roomId, entity.getId());
            if (success) {
                System.out.println("Certification given successfully to user Id:" + entity.getId());
            } else {
                System.out.println("Error giving certification to user Id:" + entity.getId());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error giving certification to user Id:" + entity.getId() + ": " + e.getMessage());
        }
    }

    private void purchaseTicket() {
        System.out.println("Purchasing ticket for user Id:" + entity.getId() + " to play a room");

        int roomId = InputHandler.readInt("Enter the Id of the room");

        try {
            boolean success = ticketService.addTicket(LocalDate.now(), roomId, entity.getId());
            if (success) {
                System.out.println("Ticket purchased successfully by user Id:" + entity.getId());
            } else {
                System.out.println("Error purchasing ticket by user Id:" + entity.getId());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error purchasing ticket by user Id:" + entity.getId() + ": " + e.getMessage());
        }
    }

    private void updateSubscription(boolean subscribed) {
        System.out.println("Updating subscription for user Id:" + entity.getId());

        try {
            boolean success = userService.updateUserSubscription(entity.getId(), subscribed);
            if (success) {
                entity.setSubscribed(subscribed);
                System.out.println("Subscription updated for user Id:" + entity.getId());
            } else {
                System.out.println("Error updating subscription for user Id:" + entity.getId());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating subscription for user Id:" + entity.getId() + ": " + e.getMessage());
        }
    }
}
