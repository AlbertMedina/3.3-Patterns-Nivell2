import db.DBConnection;
import escapeRoom.EscapeRoom;
import escapeRoom.EscapeRoomDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class EscapeRoomDAOTest {
    private static EscapeRoomDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        dao = new EscapeRoomDAO();


        Connection conn = DBConnection.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute("DELETE FROM escape_room");
    }

    @Test
    void testInsert() {
        EscapeRoom er = new EscapeRoom( "EscapeRoom 1");

        boolean result = dao.insert(er);

        assertTrue(result, "Insert must return true.");

        EscapeRoom fromDb = dao.findById(1);

        assertNotNull(fromDb, "Inserted escapeRoom must exist in data base.");
        assertEquals("EscapeRoom 1", fromDb.getName());
    }

    @Test
    void testFindAll() {
        dao.insert(new EscapeRoom( "EscapeRoom 1"));
        dao.insert(new EscapeRoom( "EscapeRoom 2"));

        var list = dao.findAll();

        assertEquals(2, list.size());
    }

    @Test
    void testFindByIdNotFound() {
        EscapeRoom er = dao.findById(999);
        assertNull(er, "No debe existir un escape room con ID 999");
    }

    @Test
    void testUpdate() {
        dao.insert(new EscapeRoom( "Old EscapeRoom"));

        EscapeRoom updated = new EscapeRoom( "New EscapeRoom");
        boolean result = dao.update(updated);

        assertTrue(result);

        EscapeRoom fromDb = dao.findById(1);
        assertEquals("New EscapeRoom", fromDb.getName());
    }

    @Test
    void testDelete() {
        dao.insert(new EscapeRoom( "EscapeRoom 12"));

        boolean result = dao.delete(1);

        assertTrue(result);

        EscapeRoom fromDb = dao.findById(1);
        assertNull(fromDb);
    }
}
