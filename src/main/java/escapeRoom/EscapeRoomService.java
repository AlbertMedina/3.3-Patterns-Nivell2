package escapeRoom;

import java.util.List;

public class EscapeRoomService {

    private final EscapeRoomDaoImpl escapeRoomDao;

    public EscapeRoomService(EscapeRoomDaoImpl escapeRoomDao) {
        this.escapeRoomDao = escapeRoomDao;
    }

    public List<EscapeRoom> getEscapeRooms() {
        return escapeRoomDao.findAll();
    }

    public EscapeRoom getEscapeRoomById(int id) {
        return escapeRoomDao.findById(id);
    }

    public boolean createEscapeRoom(String name) {
        EscapeRoom escapeRoom = new EscapeRoom(name);
        return escapeRoomDao.insert(escapeRoom);
    }


    public boolean updateEscapeRoom(int id, String newName) {
        EscapeRoom escapeRoom = escapeRoomDao.findById(id);
        if (escapeRoom == null) {
            throw new IllegalArgumentException("Escape room with id " + id + " does not exist");
        }

        escapeRoom.setName(newName);

        return escapeRoomDao.update(escapeRoom);
    }

    public boolean deleteEscapeRoom(int id) {
        return escapeRoomDao.delete(id);
    }
}

