package escapeRoom;

import java.util.List;

public class EscapeRoomService {

    private EscapeRoomDaoImpl escapeRoomDao;

    public EscapeRoomService() {

        this.escapeRoomDao = new EscapeRoomDaoImpl();
    }

    public boolean createEscapeRoom(String name) {

        EscapeRoom escapeRoom = new EscapeRoom(name);

        return escapeRoomDao.insert(escapeRoom);
    }

    public EscapeRoom getEscapeRoom(int id) {
        return escapeRoomDao.findById(id);
    }

    public List<EscapeRoom> listEscapeRooms() {
        List<EscapeRoom> rooms = escapeRoomDao.findAll();

        return rooms;
    }

    public boolean updateEscapeRoom(int id, String newName) {

        EscapeRoom escapeRoom = escapeRoomDao.findById(id);

        if (escapeRoom == null) {
            throw new IllegalArgumentException("Escape room with ID :" + id + " does not exist");
        }
        escapeRoom.setName(newName);

        return escapeRoomDao.update(escapeRoom);
    }

    public boolean deleteEscapeRoom(int id) {
        EscapeRoom er = escapeRoomDao.findById(id);
        if (er == null) return false;

        return escapeRoomDao.delete(id);
    }
}

