package room;

import escapeRoom.EscapeRoom;
import escapeRoom.EscapeRoomDaoImpl;

import java.util.List;

public class RoomService {

    private final EscapeRoomDaoImpl escapeRoomDao;
    private final RoomDaoImpl roomDao;

    public RoomService() {
        escapeRoomDao = new EscapeRoomDaoImpl();
        roomDao = new RoomDaoImpl();
    }

    public List<Room> getRooms() {
        return roomDao.findAll();
    }

    public List<Room> getRoomsByEscapeRoom(int escapeRoomId) {
        return roomDao.findAllByEscapeRoom(escapeRoomId);
    }

    public Room getRoomById(int id) {
        return roomDao.findById(id);
    }

    public boolean addRoom(String name, int difficultyValue, double price, int escapeRoomId) {
        EscapeRoom escapeRoom = escapeRoomDao.findById(escapeRoomId);
        if (escapeRoom == null) {
            throw new IllegalArgumentException("Escape room with id " + escapeRoomId + " does not exist");
        }

        Room room = new Room(name, Difficulty.fromInt(difficultyValue), price, escapeRoomId);
        return roomDao.insert(room);
    }

    public boolean updateRoom(int id, String newName, int newDifficultyValue, double newPrice, int newEscapeRoomId) {
        EscapeRoom escapeRoom = escapeRoomDao.findById(newEscapeRoomId);
        if (escapeRoom == null) {
            throw new IllegalArgumentException("Escape room with id " + newEscapeRoomId + " does not exist");
        }

        Room room = new Room(newName, Difficulty.fromInt(newDifficultyValue), newPrice, newEscapeRoomId);
        room.setId(id);
        return roomDao.update(room);
    }

    public boolean removeRoom(int id) {
        return roomDao.delete(id);
    }
}

