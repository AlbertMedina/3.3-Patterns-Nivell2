package ticket;

import db.DBConnection;
import db.GenericDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao implements GenericDao<Ticket> {

    @Override
    public Ticket findById(int id) {
        String sqlQuery = "SELECT * FROM ticket WHERE id = ?";
        Ticket ticket = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ticket = new Ticket(rs.getDate("date").toLocalDate(), rs.getDouble("price"), rs.getInt("room_id"), rs.getInt("user_id"));
                ticket.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ticket;
    }

    @Override
    public List<Ticket> findAll() {
        String sqlQuery = "SELECT * FROM ticket";

        List<Ticket> tickets = new ArrayList<>();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Ticket ticket = new Ticket(rs.getDate("date").toLocalDate(), rs.getDouble("price"), rs.getInt("room_id"), rs.getInt("user_id"));
                ticket.setId(rs.getInt("id"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tickets;
    }

    @Override
    public boolean insert(Ticket element) {
        String sql = "INSERT INTO ticket (date, price, room_id, user_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, Date.valueOf(element.getDate()));
            ps.setDouble(2, element.getPrice());
            ps.setInt(3, element.getRoomId());
            ps.setInt(4, element.getUserId());

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
    public boolean update(Ticket element) {
        String sql = "UPDATE ticket SET date = ?, price = ?, room_id = ?, user_id = ? WHERE id = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(element.getDate()));
            ps.setDouble(2, element.getPrice());
            ps.setInt(3, element.getRoomId());
            ps.setInt(4, element.getUserId());
            ps.setInt(5, element.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sqlQuery = "DELETE FROM ticket WHERE id = ?";

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

