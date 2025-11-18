package room;

import db.DBConnection;
import db.GenericDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDao implements GenericDao<Room> {

    @Override
    public Room findById(int id) {
        String sqlQuery = "SELECT * FROM rooms WHERE id = ?";
        Room room = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                room = new Room(rs.getString("name"), Enum.valueOf(Difficulty.class, rs.getString("difficulty")), rs.getDouble("price"));
                room.setId(rs.getInt("id"));
                room.setEscapeRoomId(rs.getInt("escape_room_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return room;
    }

    @Override
    public List<Room> findAll() {
        String sqlQuery = "SELECT * FROM rooms";

        List<Room> rooms = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Room room = new Room(rs.getString("name"), Enum.valueOf(Difficulty.class, rs.getString("difficulty")), rs.getDouble("price"));
                room.setId(rs.getInt("id"));
                room.setEscapeRoomId(rs.getInt("escape_room_id"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rooms;
    }

    @Override
    public boolean insert(Room element) {
        String sql = "INSERT INTO rooms (name, difficulty, price, escape_room_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, element.getName());
            ps.setString(2, element.getDifficulty().name());
            ps.setDouble(3, element.getPrice());
            ps.setInt(4, element.getEscapeRoomId());

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
    public boolean update(Room element) {
        String sql = "UPDATE rooms SET name = ?, difficulty = ?, price = ?, escape_room_id = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, element.getName());
            ps.setString(2, element.getDifficulty().name());
            ps.setDouble(3, element.getPrice());
            ps.setInt(4, element.getEscapeRoomId());
            ps.setInt(5, element.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sqlQuery = "DELETE FROM rooms WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}
