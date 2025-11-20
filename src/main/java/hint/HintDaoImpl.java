package hint;

import db.DBConnection;
import db.GenericDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class HintDaoImpl implements GenericDao<Hint> {

    private Connection getConnection() throws SQLException {
        return DBConnection.getInstance().getConnection();
    }

    private Hint map(ResultSet resultSet) throws SQLException {
        Hint hint = new Hint(
                resultSet.getString("text"),
                resultSet.getString("theme"),
                resultSet.getDouble("value"),
                resultSet.getInt("room_id")
        );
        hint.setId(resultSet.getInt("id"));
        return hint;
    }

    @Override
    public Hint findById(int id) {
        String sql = "Select * From hint WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return map(resultSet);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error finding Hint by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Hint> findAll() {
        List<Hint> hints = new ArrayList<>();
        String sql = "SELECT * FROM hint";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                hints.add(map(resultSet));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching Hints: " + e.getMessage());
        }
        return hints;
    }

    @Override
    public boolean insert(Hint hint) {

        String sql = "INSERT INTO hint (text, theme, value, room_id) VALUES (?,?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, hint.getText());
            statement.setString(2, hint.getTheme());
            statement.setDouble(3, hint.getValue());
            statement.setInt(4, hint.getRoomId());

            int rows = statement.executeUpdate();
            if (rows > 0) {
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    hint.setId(keys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error inserting" + e.getMessage());

        }
        return false;
    }

    @Override
    public boolean delete(int id) {

        String sql = "DELETE FROM hint WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;


        } catch (SQLException e) {
            System.out.println("Error deleting Hint: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Hint hint) {
        String sql = """
                UPDATE hint
                SET text = ?, theme = ?, value = ?, room_id = ?
                WHERE id = ?
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, hint.getText());
            statement.setString(2, hint.getTheme());
            statement.setDouble(3, hint.getValue());
            statement.setInt(4, hint.getRoomId());
            statement.setInt(5, hint.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating" + e.getMessage());
        }
        return false;
    }
}
