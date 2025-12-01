package room;

import escapeRoom.EscapeRoom;
import escapeRoom.EscapeRoomDaoImpl;

import java.util.List;

public class RoomService {

    private final EscapeRoomDaoImpl escapeRoomDao;
    private final RoomDaoImpl roomDao;

    public RoomService(EscapeRoomDaoImpl escapeRoomDao, RoomDaoImpl roomDao) {
        this.escapeRoomDao = escapeRoomDao;
        this.roomDao = roomDao;
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

        Room room = new Room(name, difficultyValue, price, escapeRoomId);
        return roomDao.insert(room);
    }

    public boolean updateRoom(int id, String newName, int newDifficultyValue, double newPrice, int newEscapeRoomId) {
        Room room = roomDao.findById(id);
        if (room == null) {
            throw new IllegalArgumentException("Room with id " + id + " does not exist");
        }

        EscapeRoom escapeRoom = escapeRoomDao.findById(newEscapeRoomId);
        if (escapeRoom == null) {
            throw new IllegalArgumentException("Escape room with id " + newEscapeRoomId + " does not exist");
        }

        room.setName(newName);
        room.setDifficulty(newDifficultyValue);
        room.setPrice(newPrice);
        room.setEscapeRoomId(newEscapeRoomId);

        return roomDao.update(room);
    }

    public boolean removeRoom(int id) {
        return roomDao.delete(id);
    }
}

