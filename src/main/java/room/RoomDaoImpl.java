package room;

import db.DBConnection;
import db.GenericDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements GenericDao<Room> {

    @Override
    public Room findById(int id) {
        String sql = "SELECT * FROM room WHERE id = ?";

        Room room = null;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                room = new Room(rs.getString("name"), Enum.valueOf(Difficulty.class, rs.getString("difficulty")), rs.getDouble("price"), rs.getInt("escape_room_id"));
                room.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return room;
    }

    @Override
    public List<Room> findAll() {
        String sql = "SELECT * FROM room";

        List<Room> rooms = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Room room = new Room(rs.getString("name"), Enum.valueOf(Difficulty.class, rs.getString("difficulty")), rs.getDouble("price"), rs.getInt("escape_room_id"));
                room.setId(rs.getInt("id"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rooms;
    }

    public List<Room> findAllByEscapeRoom(int escapeRoomId) {
        String sql = "SELECT * FROM room WHERE escape_room_id = ?";

        List<Room> rooms = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, escapeRoomId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Room room = new Room(rs.getString("name"), Difficulty.valueOf(rs.getString("difficulty")), rs.getDouble("price"), rs.getInt("escape_room_id"));
                    room.setId(rs.getInt("id"));
                    rooms.add(room);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return rooms;
    }

    @Override
    public boolean insert(Room element) {
        String sql = "INSERT INTO room (name, difficulty, price, escape_room_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
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
        String sql = "UPDATE room SET name = ?, difficulty = ?, price = ?, escape_room_id = ? WHERE id = ?";

        try (Connection connection = getConnection();
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
        String sql = "DELETE FROM room WHERE id = ?";

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
