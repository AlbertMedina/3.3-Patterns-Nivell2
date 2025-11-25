package hint;

import room.Room;
import room.RoomDaoImpl;

import java.util.List;

public class HintService {

    private final RoomDaoImpl roomDao;
    private final HintDaoImpl hintDao;

    public HintService() {
        roomDao = new RoomDaoImpl();
        hintDao = new HintDaoImpl();
    }

    public List<Hint> getHints() {
        return hintDao.findAll();
    }

    public List<Hint> getHintsByRoom(int roomId) {
        return hintDao.findAllByRoom(roomId);
    }

    public Hint getHintById(int id) {
        return hintDao.findById(id);
    }

    public boolean addHint(String text, String theme, double value, int roomId) {
        Room room = roomDao.findById(roomId);
        if (room == null) {
            throw new IllegalArgumentException("Room with id " + roomId + " does not exist");
        }

        Hint hint = new Hint(text, theme, value, roomId);
        return hintDao.insert(hint);
    }

    public boolean updateHint(String text, String theme, double value, int roomId) {
        Room room = roomDao.findById(roomId);
        if (room == null) {
            throw new IllegalArgumentException("Room with id " + roomId + " does not exist");
        }

        Hint hint = new Hint(text, theme, value, roomId);
        return hintDao.update(hint);
    }

    public boolean removeHint(int id) {
        return hintDao.delete(id);
    }
}
