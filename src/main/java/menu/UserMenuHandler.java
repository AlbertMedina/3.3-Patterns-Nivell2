package menu;

import certification.Certification;
import certification.CertificationService;
import input.InputHandler;
import reward.Reward;
import reward.RewardService;
import ticket.Ticket;
import ticket.TicketService;
import user.User;
import user.UserService;

import java.time.LocalDate;
import java.util.List;

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
        System.out.println("4. Show tickets");
        System.out.println("5. Give certification");
        System.out.println("6. Show certifications");
        System.out.println("7. Give reward");
        System.out.println("8. Show rewards");
        System.out.println("0. Back");
        return InputHandler.readInt("Choose what to do next (0-8)");
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
                showTickets();
                break;
            case 5:
                giveCertification();
                break;
            case 6:
                showCertifications();
                break;
            case 7:
                giveReward();
                break;
            case 8:
                showRewards();
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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            System.out.println("Error updating subscription for user Id:" + entity.getId() + ": " + e.getMessage());
        }
    }

    private void showTickets() {
        List<Ticket> tickets = ticketService.getTicketsByUser(entity.getId());

        if (tickets.isEmpty()) {
            System.out.println("There are no tickets purchased by this user");
        } else {
            System.out.println("📌 Tickets purchased by user Id:" + entity.getId() + " (" + entity.getName() + "):");
            tickets.forEach(System.out::println);
        }
    }

    private void showCertifications() {
        List<Certification> certifications = certificationService.getCertificationsByUser(entity.getId());

        if (certifications.isEmpty()) {
            System.out.println("There are no certifications granted to this user");
        } else {
            System.out.println("📌 Certifications granted to user Id:" + entity.getId() + " (" + entity.getName() + "):");
            certifications.forEach(System.out::println);
        }
    }

    private void showRewards() {
        List<Reward> rewards = rewardService.getRewardsByUser(entity.getId());

        if (rewards.isEmpty()) {
            System.out.println("There are no rewards granted to this user");
        } else {
            System.out.println("📌 Rewards granted to user Id:" + entity.getId() + " (" + entity.getName() + "):");
            rewards.forEach(System.out::println);
        }
    }
}
