package Inventory;

import decoration.Decoration;
import decoration.DecorationService;
import hint.Hint;
import hint.HintService;
import room.Room;
import room.RoomService;

import java.util.List;

public class InventoryService {

    private final RoomService roomService;
    private final DecorationService decorationService;
    private final HintService hintService;

    public InventoryService() {
        this.roomService = new RoomService();
        this.decorationService = new DecorationService();
        this.hintService = new HintService();
    }

    public Inventory getFullInventory() {
        List<Room> rooms = roomService.getRooms();
        List<Decoration> decorations = decorationService.getDecorations();
        List<Hint> hints = hintService.getHints();

        return new Inventory(rooms, decorations, hints);
    }
}
