package decoration;

import db.DBConnection;
import db.GenericDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DecorationDaoImpl implements GenericDao<Decoration> {

    @Override
    public Decoration findById(int id) {
        String sql = "SELECT * FROM decoration WHERE id = ?";

        Decoration decoration = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                decoration = new Decoration(rs.getString("name"), rs.getString("material"), rs.getDouble("value"), rs.getInt("room_id"));
                decoration.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return decoration;
    }

    @Override
    public List<Decoration> findAll() {
        String sql = "SELECT * FROM decoration";

        List<Decoration> decorations = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Decoration decoration = new Decoration(rs.getString("name"), rs.getString("material"), rs.getDouble("value"), rs.getInt("room_id"));
                decoration.setId(rs.getInt("id"));
                decorations.add(decoration);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return decorations;
    }

    public List<Decoration> findAllByRoom(int roomId) {
        String sql = "SELECT * FROM decoration WHERE room_id = ?";

        List<Decoration> decorations = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, roomId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Decoration decoration = new Decoration(rs.getString("name"), rs.getString("material"), rs.getDouble("value"), rs.getInt("room_id"));
                    decoration.setId(rs.getInt("id"));
                    decorations.add(decoration);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return decorations;
    }

    @Override
    public boolean insert(Decoration element) {
        String sql = "INSERT INTO decoration (name, material, value, room_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, element.getName());
            ps.setString(2, element.getMaterial());
            ps.setDouble(3, element.getValue());
            ps.setInt(4, element.getRoomId());

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
    public boolean update(Decoration element) {
        String sql = "UPDATE decoration SET name = ?, material = ?, value = ?, room_id = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, element.getName());
            ps.setString(2, element.getMaterial());
            ps.setDouble(3, element.getValue());
            ps.setInt(4, element.getRoomId());
            ps.setInt(5, element.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM decoration WHERE id = ?";

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
