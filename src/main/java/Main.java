import Inventory.InventoryService;
import certification.CertificationDaoImpl;
import certification.CertificationService;
import decoration.DecorationDaoImpl;
import decoration.DecorationService;
import escapeRoom.EscapeRoomDaoImpl;
import escapeRoom.EscapeRoomService;
import hint.HintDaoImpl;
import hint.HintService;
import input.InputHandler;
import menu.MainMenuHandler;
import reward.RewardDaoImpl;
import reward.RewardService;
import room.RoomDaoImpl;
import room.RoomService;
import ticket.TicketDaoImpl;
import ticket.TicketService;
import user.UserDaoImpl;
import user.UserService;

public class Main {
    public static void main(String[] args) {

        EscapeRoomDaoImpl escapeRoomDao = new EscapeRoomDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();
        RoomDaoImpl roomDao = new RoomDaoImpl();
        HintDaoImpl hintDao = new HintDaoImpl();
        DecorationDaoImpl decorationDao = new DecorationDaoImpl();
        TicketDaoImpl ticketDao = new TicketDaoImpl();
        RewardDaoImpl rewardDao = new RewardDaoImpl();
        CertificationDaoImpl certificationDao = new CertificationDaoImpl();

        EscapeRoomService escapeRoomService = new EscapeRoomService(escapeRoomDao);
        UserService userService = new UserService(userDao);
        RoomService roomService = new RoomService(escapeRoomDao, roomDao);
        HintService hintService = new HintService(roomDao, hintDao);
        DecorationService decorationService = new DecorationService(roomDao, decorationDao);
        InventoryService inventoryService = new InventoryService(roomService, hintService, decorationService);
        TicketService ticketService = new TicketService(ticketDao, roomDao, userDao);
        RewardService rewardService = new RewardService(rewardDao, userDao);
        CertificationService certificationService = new CertificationService(certificationDao, roomDao, userDao);

        MainMenuHandler mainMenuHandler = new MainMenuHandler(escapeRoomService, userService, inventoryService, roomService, hintService, decorationService, ticketService, rewardService, certificationService);
        mainMenuHandler.run();

        InputHandler.closeScanner();
    }
}
