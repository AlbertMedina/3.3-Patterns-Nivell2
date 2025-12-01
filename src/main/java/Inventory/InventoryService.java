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
    private final HintService hintService;
    private final DecorationService decorationService;

    public InventoryService(RoomService roomService, HintService hintService, DecorationService decorationService) {
        this.roomService = roomService;
        this.hintService = hintService;
        this.decorationService = decorationService;
    }

    public Inventory getFullInventory() {
        List<Room> rooms = roomService.getRooms();
        List<Decoration> decorations = decorationService.getDecorations();
        List<Hint> hints = hintService.getHints();

        return new Inventory(rooms, decorations, hints);
    }
}
