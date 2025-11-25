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

    public boolean addRoom(String name, Difficulty difficulty, double price, int escapeRoomId) {
        EscapeRoom escapeRoom = escapeRoomDao.findById(escapeRoomId);
        if (escapeRoom == null) {
            throw new IllegalArgumentException("Escape room with id " + escapeRoomId + " does not exist");
        }

        Room room = new Room(name, difficulty, price, escapeRoomId);
        return roomDao.insert(room);
    }

    public boolean updateRoom(String name, Difficulty difficulty, double price, int escapeRoomId) {
        EscapeRoom escapeRoom = escapeRoomDao.findById(escapeRoomId);
        if (escapeRoom == null) {
            throw new IllegalArgumentException("Escape room with id " + escapeRoomId + " does not exist");
        }

        Room room = new Room(name, difficulty, price, escapeRoomId);
        return roomDao.update(room);
    }

    public boolean removeRoom(int id) {
        return roomDao.delete(id);
    }
}

