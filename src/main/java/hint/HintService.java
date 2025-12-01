package hint;

import room.Room;
import room.RoomDaoImpl;

import java.util.List;

public class HintService {

    private final RoomDaoImpl roomDao;
    private final HintDaoImpl hintDao;

    public HintService(RoomDaoImpl roomDao, HintDaoImpl hintDao) {
        this.roomDao = roomDao;
        this.hintDao = hintDao;
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

    public boolean updateHint(int id, String newText, String newTheme, double newValue, int newRoomId) {
        Hint hint = hintDao.findById(id);
        if (hint == null) {
            throw new IllegalArgumentException("Hint with id " + id + " does not exist");
        }

        Room room = roomDao.findById(newRoomId);
        if (room == null) {
            throw new IllegalArgumentException("Room with id " + newRoomId + " does not exist");
        }

        hint.setText(newText);
        hint.setTheme(newTheme);
        hint.setValue(newValue);
        hint.setRoomId(newRoomId);

        return hintDao.update(hint);
    }

    public boolean removeHint(int id) {
        return hintDao.delete(id);
    }
}
