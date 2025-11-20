package escapeRoom;

import db.DBConnection;
import db.GenericDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscapeRoomDAO implements GenericDao<EscapeRoom> {

    @Override
    public EscapeRoom findById(int id) {
        String sql = "SELECT id, name FROM escape_room WHERE id = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    EscapeRoom escapeRoom = new EscapeRoom(rs.getString("name"));
                    escapeRoom.setId(rs.getInt("id"));
                            return escapeRoom;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<EscapeRoom> findAll() {
        List<EscapeRoom> escapeRoomList = new ArrayList<>();
        String sql = "SELECT id, name FROM escape_room";

        try (Connection connection = DBConnection.getInstance().getConnection();
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

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, element.getName());

            int rows = ps.executeUpdate();
            if (rows == 0) return false;

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    element.setId(keys.getInt(1));
                }
            }

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(EscapeRoom element) {
        String sql = "UPDATE escape_room SET name = ? WHERE id = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
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

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
