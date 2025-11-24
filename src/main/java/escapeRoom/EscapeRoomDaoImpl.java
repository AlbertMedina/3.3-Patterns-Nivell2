package escapeRoom;

import db.DBConnection;
import db.GenericDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscapeRoomDaoImpl implements GenericDao<EscapeRoom> {

    @Override
    public EscapeRoom findById(int id) {
        String sql = "SELECT id, name FROM escape_room WHERE id = ?";

        EscapeRoom escapeRoom = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                escapeRoom = new EscapeRoom(rs.getString("name"));
                escapeRoom.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return escapeRoom;
    }

    @Override
    public List<EscapeRoom> findAll() {
        String sql = "SELECT id, name FROM escape_room";

        List<EscapeRoom> escapeRoomList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EscapeRoom escapeRoom = new EscapeRoom(rs.getString("name"));
                escapeRoom.setId(rs.getInt("id"));
                escapeRoomList.add(escapeRoom);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return escapeRoomList;
    }

    @Override
    public boolean insert(EscapeRoom element) {
        String sql = "INSERT INTO escape_room (name) VALUES (?)";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, element.getName());

            int rowsAffected = ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                element.setId(keys.getInt(1));
            }

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(EscapeRoom element) {
        String sql = "UPDATE escape_room SET name = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, element.getName());
            ps.setInt(2, element.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM escape_room WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    private Connection getConnection() throws SQLException {
        return DBConnection.getInstance().getConnection();
    }

}
