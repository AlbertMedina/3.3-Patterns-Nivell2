package certification;

import room.Room;
import room.RoomDaoImpl;
import user.User;
import user.UserDaoImpl;

import java.time.LocalDate;
import java.util.List;

public class CertificationService {

    private final CertificationDaoImpl certificationDao;
    private final RoomDaoImpl roomDao;
    private final UserDaoImpl userDao;

    public CertificationService() {
        certificationDao = new CertificationDaoImpl();
        roomDao = new RoomDaoImpl();
        userDao = new UserDaoImpl();
    }

    public List<Certification> getCertifications() {
        return certificationDao.findAll();
    }

    public Certification getCertificationById(int id) {
        return certificationDao.findById(id);
    }

    public boolean addCertification(String name, LocalDate date, int roomId, int userId) {
        Room room = roomDao.findById(roomId);
        if (room == null) {
            throw new IllegalArgumentException("Room with id " + roomId + " does not exist");
        }

        User user = userDao.findById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User with id " + userId + " does not exist");
        }

        Certification certification = new Certification(name, date, roomId, userId);
        return certificationDao.insert(certification);
    }

    public boolean updateCertification(int id, String newName, LocalDate newDate, int newRoomId, int newUserId) {
        Certification certification = certificationDao.findById(id);
        if (certification == null) {
            throw new IllegalArgumentException("Certification with id " + id + " does not exist");
        }

        Room room = roomDao.findById(newRoomId);
        if (room == null) {
            throw new IllegalArgumentException("Room with id " + newRoomId + " does not exist");
        }

        User user = userDao.findById(newUserId);
        if (user == null) {
            throw new IllegalArgumentException("User with id " + newUserId + " does not exist");
        }

        certification.setName(newName);
        certification.setDate(newDate);
        certification.setRoomId(newRoomId);
        certification.setUserId(newUserId);

        return certificationDao.update(certification);
    }

    public boolean deleteCertification(int id) {
        return certificationDao.delete(id);
    }
}

