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

    public boolean updateEscapeRoom(String newName) {

        return escapeRoomDao.update(new EscapeRoom(newName));
    }

    public boolean deleteEscapeRoom(int id) {
        EscapeRoom er = escapeRoomDao.findById(id);
        if (er == null) return false;

        return escapeRoomDao.delete(id);
    }
}

