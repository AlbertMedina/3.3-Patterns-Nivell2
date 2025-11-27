package hint;

import db.DBConnection;
import db.GenericDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HintDaoImpl implements GenericDao<Hint> {

    @Override
    public Hint findById(int id) {
        String sql = "Select * From hint WHERE id = ?";

        Hint hint = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                hint = new Hint(rs.getString("text"), rs.getString("theme"), rs.getDouble("value"), rs.getInt("room_id"));
                hint.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("Error finding Hint by ID: " + e.getMessage());
        }

        return hint;
    }

    @Override
    public List<Hint> findAll() {
        String sql = "SELECT * FROM hint";

        List<Hint> hints = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Hint hint = new Hint(rs.getString("text"), rs.getString("theme"), rs.getDouble("value"), rs.getInt("room_id"));
                hint.setId(rs.getInt("id"));
                hints.add(hint);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching Hints: " + e.getMessage());
        }

        return hints;
    }

    public List<Hint> findAllByRoom(int roomId) {
        String sql = "SELECT * FROM hint WHERE room_id = ?";

        List<Hint> hints = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, roomId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Hint hint = new Hint(rs.getString("text"), rs.getString("theme"), rs.getDouble("value"), rs.getInt("room_id"));
                    hint.setId(rs.getInt("id"));
                    hints.add(hint);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return hints;
    }

    @Override
    public boolean insert(Hint hint) {
        String sql = "INSERT INTO hint (text, theme, value, room_id) VALUES (?,?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, hint.getText());
            ps.setString(2, hint.getTheme());
            ps.setDouble(3, hint.getValue());
            ps.setInt(4, hint.getRoomId());

            int rowsAffected = ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                hint.setId(rs.getInt(1));
            }

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error inserting" + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM hint WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting Hint: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(Hint hint) {
        String sql = " UPDATE hint SET text = ?, theme = ?, value = ?, room_id = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, hint.getText());
            ps.setString(2, hint.getTheme());
            ps.setDouble(3, hint.getValue());
            ps.setInt(4, hint.getRoomId());
            ps.setInt(5, hint.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating" + e.getMessage());
        }

        return false;
    }

    private Connection getConnection() throws SQLException {
        return DBConnection.getInstance().getConnection();
    }
}
