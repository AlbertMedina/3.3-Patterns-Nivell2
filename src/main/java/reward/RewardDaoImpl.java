package reward;

import db.DBConnection;
import db.GenericDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RewardDaoImpl implements GenericDao<Reward> {

    @Override
    public Reward findById(int id) {
        String sql = "SELECT * FROM reward WHERE id = ?";

        Reward reward = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                reward = new Reward(rs.getString("name"), rs.getString("description"), rs.getDate("date").toLocalDate(), rs.getInt("user_id"));
                reward.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("Error finding Reward by ID: " + e.getMessage());
        }

        return reward;
    }

    @Override
    public List<Reward> findAll() {
        String sql = "SELECT * FROM reward";

        List<Reward> rewards = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reward reward = new Reward(rs.getString("name"), rs.getString("description"), rs.getDate("date").toLocalDate(), rs.getInt("user_id"));
                reward.setId(rs.getInt("id"));
                rewards.add(reward);
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
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, reward.getName());
            ps.setString(2, reward.getDescription());
            ps.setDate(3, Date.valueOf(reward.getDate()));
            ps.setInt(4, reward.getUserId());

            int rowsAffected = ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                reward.setId(rs.getInt(1));
            }

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error inserting Reward: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(Reward reward) {
        String sql = "UPDATE reward SET name = ?, description = ?, date = ?, user_id = ? vWHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, reward.getName());
            ps.setString(2, reward.getDescription());
            ps.setDate(3, Date.valueOf(reward.getDate()));
            ps.setInt(4, reward.getUserId());
            ps.setInt(5, reward.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating Reward: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM reward WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting Reward: " + e.getMessage());
        }

        return false;
    }

    private Connection getConnection() throws SQLException {
        return DBConnection.getInstance().getConnection();
    }
}


