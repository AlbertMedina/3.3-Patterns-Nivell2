package certification;

import reward.Reward;

import java.time.LocalDate;
import java.util.List;

public class CertificationService {
    private CertificationDaoImpl certificationDao;

    public CertificationService(){
        this.certificationDao = new CertificationDaoImpl();
    }

    public boolean addCertification(String name, LocalDate date, int roomId, int userId) {
        Certification certification = new Certification(name, date, roomId, userId);

        return certificationDao.insert(certification);
    }

    public Certification getCertification(int id) {
        return certificationDao.findById(id);
    }

    public List<Certification> showCertifications(){
        return certificationDao.findAll();
    }

    public boolean updateCertification(int id, String newName, LocalDate newDate, int newRoomId, int newUserId) {
        Certification certification = certificationDao.findById(id);
        if (certification == null) return false;

        certification.setName(newName);
        certification.setDate(newDate);
        certification.setRoomId(newRoomId);
        certification.setUserId(newUserId);
        return certificationDao.update(certification);
    }

    public boolean deleteCertification(int id) {
        Certification certification = certificationDao.findById(id);
        if (certification == null) return false;

        return certificationDao.delete(id);
    }
}

