package escapeRoom;

import exceptions.EscapeRoomNotFoundException;

import java.util.List;

public class EscapeRoomService {

    private final EscapeRoomDaoImpl escapeRoomDao;

    public EscapeRoomService() {
        this.escapeRoomDao = new EscapeRoomDaoImpl();
    }

    public List<EscapeRoom> getEscapeRooms() {
        return escapeRoomDao.findAll();
    }

    public EscapeRoom getEscapeRoomById(int id) {
        EscapeRoom room = escapeRoomDao.findById(id);
        if (room == null) {
            throw new EscapeRoomNotFoundException("ID: " + id);
        }
        return room;
    }

    public boolean createEscapeRoom(String name) {
        EscapeRoom escapeRoom = new EscapeRoom(name);
        return escapeRoomDao.insert(escapeRoom);
    }


    public boolean updateEscapeRoom(int id, String newName) {
        EscapeRoom escapeRoom = escapeRoomDao.findById(id);
        if (escapeRoom == null) {
            throw new EscapeRoomNotFoundException("ID: " + id);
        }

        escapeRoom.setName(newName);

        return escapeRoomDao.update(escapeRoom);
    }

    public boolean deleteEscapeRoom(int id) {
        return escapeRoomDao.delete(id);
    }
}

