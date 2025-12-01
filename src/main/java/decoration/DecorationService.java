package decoration;

import room.Room;
import room.RoomDaoImpl;

import java.util.List;

public class DecorationService {

    private final RoomDaoImpl roomDao;
    private final DecorationDaoImpl decorationDao;

    public DecorationService(RoomDaoImpl roomDao, DecorationDaoImpl decorationDao) {
        this.roomDao = roomDao;
        this.decorationDao = decorationDao;
    }

    public List<Decoration> getDecorations() {
        return decorationDao.findAll();
    }

    public List<Decoration> getDecorationsByRoom(int roomId) {
        return decorationDao.findAllByRoom(roomId);
    }

    public Decoration getDecorationById(int id) {
        return decorationDao.findById(id);
    }

    public boolean addDecoration(String name, String material, double value, int roomId) {
        Room room = roomDao.findById(roomId);
        if (room == null) {
            throw new IllegalArgumentException("Room with id " + roomId + " does not exist");
        }

        Decoration decoration = new Decoration(name, material, value, roomId);
        return decorationDao.insert(decoration);
    }

    public boolean updateDecoration(int id, String newName, String newMaterial, double newValue, int newRoomId) {
        Decoration decoration = decorationDao.findById(id);
        if (decoration == null) {
            throw new IllegalArgumentException("Ticket with id " + id + " does not exist");
        }

        Room room = roomDao.findById(newRoomId);
        if (room == null) {
            throw new IllegalArgumentException("Room with id " + newRoomId + " does not exist");
        }

        decoration.setName(newName);
        decoration.setMaterial(newMaterial);
        decoration.setValue(newValue);
        decoration.setRoomId(newRoomId);

        return decorationDao.update(decoration);
    }

    public boolean removeDecoration(int id) {
        return decorationDao.delete(id);
    }
}
