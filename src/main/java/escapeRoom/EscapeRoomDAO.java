package escapeRoom;

import db.DBConnection;
import db.GenericDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscapeRoomDAO implements GenericDao<EscapeRoom> {



    @Override
    public EscapeRoom findById(int id) {
        String sql =  "SELECT id, name FROM escape_room WHERE id = ?";
        try(Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                return new EscapeRoom(
                        result.getInt("id"),
                        result.getString("name")
                );
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
        try (Connection connection = DBConnection.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            while (result.next()){
                escapeRoomList.add(new EscapeRoom(
                        result.getInt("id"),
                        result.getString("name")
                ));
            }
        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }

        return escapeRoomList;
    }

    @Override
    public boolean insert(EscapeRoom element) {
        String sql = "INSERT INTO escape_room (id, name) VALUES (?, ?)";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,element.getId());
            ps.setString(2,element.getName());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(EscapeRoom element) {
        String sql =   "UPDATE escape_room SET name = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, element.getName());
            ps.setInt(2,element.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM escape_room WHERE id = ?";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() >0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
