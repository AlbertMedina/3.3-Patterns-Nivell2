package certification;

import db.DBConnection;
import db.GenericDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CertificationDaoImpl implements GenericDao<Certification> {

    @Override
    public Certification findById(int id) {
        String sql = "SELECT * FROM certification WHERE id = ?";

        Certification certification = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                certification = new Certification(rs.getString("name"), rs.getDate("date").toLocalDate(), rs.getInt("room_id"), rs.getInt("user_id"));
                certification.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return certification;
    }

    @Override
    public List<Certification> findAll() {
        String sql = "SELECT * FROM certification";

        List<Certification> certifications = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Certification certification = new Certification(rs.getString("name"), rs.getDate("date").toLocalDate(), rs.getInt("room_id"), rs.getInt("user_id"));
                certification.setId(rs.getInt("id"));
                certifications.add(certification);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return certifications;
    }

    public List<Certification> findAllByRoom(int roomId) {
        String sql = "SELECT * FROM certification WHERE room_id = ?";

        List<Certification> certifications = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, roomId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Certification certification = new Certification(rs.getString("name"), rs.getDate("date").toLocalDate(), rs.getInt("room_id"), rs.getInt("user_id"));
                    certification.setId(rs.getInt("id"));
                    certifications.add(certification);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return certifications;
    }

    public List<Certification> findAllByUser(int userId) {
        String sql = "SELECT * FROM certification WHERE user_id = ?";

        List<Certification> certifications = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Certification certification = new Certification(rs.getString("name"), rs.getDate("date").toLocalDate(), rs.getInt("room_id"), rs.getInt("user_id"));
                    certification.setId(rs.getInt("id"));
                    certifications.add(certification);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return certifications;
    }

    @Override
    public boolean insert(Certification element) {
        String sql = "INSERT INTO certification (name, date, room_id, user_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, element.getName());
            ps.setDate(2, Date.valueOf(element.getDate()));
            ps.setInt(3, element.getRoomId());
            ps.setInt(4, element.getUserId());

            int rowsAffected = ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                element.setId(rs.getInt(1));
            }

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(Certification element) {
        String sql = "UPDATE certification SET name = ?, date = ?, room_id = ?, user_id = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, element.getName());
            ps.setDate(2, Date.valueOf(element.getDate()));
            ps.setInt(3, element.getRoomId());
            ps.setInt(4, element.getUserId());
            ps.setInt(5, element.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM certification WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    private Connection getConnection() throws SQLException {
        return DBConnection.getInstance().getConnection();
    }
}
