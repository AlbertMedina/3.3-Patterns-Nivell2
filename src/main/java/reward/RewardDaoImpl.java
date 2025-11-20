package reward;

import db.DBConnection;
import db.GenericDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RewardDaoImpl implements GenericDao<Reward> {

    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }

    private Reward map(ResultSet resultSet) throws SQLException {
        Reward reward = new Reward(
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getDate("date").toLocalDate(),
                resultSet.getInt("user_id")
        );
        reward.setId(resultSet.getInt("id"));
        return reward;
    }

    @Override
    public Reward findById(int id) {

        String sql = "SELECT * FROM reward WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return map(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding Reward by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Reward> findAll() {

        List<Reward> rewards = new ArrayList<>();
        String sql = "SELECT * FROM reward";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                rewards.add(map(resultSet));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching Rewards: " + e.getMessage());
        }
        return rewards;
    }

    @Override
    public boolean insert(Reward reward) {
        String sql = "INSERT INTO reward (name, description, date, user_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, reward.getName());
            statement.setString(2, reward.getDescription());
            statement.setDate(3, Date.valueOf(reward.getDate()));
            statement.setInt(4, reward.getUserId());

            int rows = statement.executeUpdate();
            if (rows > 0) {
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    reward.setId(keys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error inserting Reward: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Reward reward) {

        String sql = """
                UPDATE reward
                SET name = ?, description = ?, date = ?, user_id = ?
                WHERE id = ?
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, reward.getName());
            statement.setString(2, reward.getDescription());
            statement.setDate(3, Date.valueOf(reward.getDate()));
            statement.setInt(4, reward.getUserId());
            statement.setInt(5, reward.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating Reward: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {

        String sql = "DELETE FROM reward WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting Reward: " + e.getMessage());
        }
        return false;
    }
}


