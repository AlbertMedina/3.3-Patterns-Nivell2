package user;

import db.DBConnection;
import db.GenericDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements GenericDao<User> {

    @Override
    public User findById(int id) {
        String sqlQuery = "SELECT * FROM user WHERE id = ?";
        User user = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("name"), rs.getString("surnames"), rs.getString("email"));
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        String sqlQuery = "SELECT * FROM user";

        List<User> users = new ArrayList<>();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("surnames"), rs.getString("email"));
                user.setId(rs.getInt("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public boolean insert(User element) {
        String sql = "INSERT INTO user (name, surnames, email) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, element.getName());
            ps.setString(2, element.getSurnames());
            ps.setString(3, element.getEmail());

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
    public boolean update(User element) {
        String sql = "UPDATE user SET name = ?, surnames = ?, email = ? WHERE id = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, element.getName());
            ps.setString(2, element.getSurnames());
            ps.setString(3, element.getEmail());
            ps.setInt(4, element.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sqlQuery = "DELETE FROM user WHERE id = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}

