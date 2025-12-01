package certification;

import exceptions.NotFoundException;
import exceptions.RoomNotFoundException;
import exceptions.UserNotFoundException;
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

    public CertificationService(CertificationDaoImpl certificationDao, RoomDaoImpl roomDao, UserDaoImpl userDao) {
        this.certificationDao = certificationDao;
        this.roomDao = roomDao;
        this.userDao = userDao;
    }

    public List<Certification> getCertifications() {
        return certificationDao.findAll();
    }

    public List<Certification> getCertificationsByRoom(int roomId) {
        return certificationDao.findAllByRoom(roomId);
    }

    public List<Certification> getCertificationsByUser(int userId) {
        return certificationDao.findAllByUser(userId);
    }

    public Certification getCertificationById(int id) {
        return certificationDao.findById(id);
    }

    public boolean addCertification(String name, LocalDate date, int roomId, int userId) {
        Room room = roomDao.findById(roomId);
        if (room == null) {
            throw new RoomNotFoundException("ID: " + roomId);
        }

        User user = userDao.findById(userId);
        if (user == null) {
            throw new UserNotFoundException("ID: " + userId);
        }

        Certification certification = new Certification(name, date, roomId, userId);
        return certificationDao.insert(certification);
    }

    public boolean updateCertification(int id, String newName, LocalDate newDate, int newRoomId, int newUserId) {
        Certification certification = certificationDao.findById(id);
        if (certification == null) {
            throw new NotFoundException("Certification with ID: " + id + " was not found.");
        }

        Room room = roomDao.findById(newRoomId);
        if (room == null) {
            throw new RoomNotFoundException("ID: " + newRoomId);
        }

        User user = userDao.findById(newUserId);
        if (user == null) {
            throw new UserNotFoundException("ID: " + newUserId);
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

