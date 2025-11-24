import db.DBConnection;
import escapeRoom.EscapeRoom;
import escapeRoom.EscapeRoomDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class EscapeRoomDaoImplImplTest {

    private static EscapeRoomDaoImpl dao;

    @BeforeEach
    void setUp() throws Exception {
        dao = new EscapeRoomDaoImpl();

        Connection conn = DBConnection.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute("DELETE FROM escape_room");
    }

    @Test
    void testInsert() {
        EscapeRoom er = new EscapeRoom("EscapeRoom 1");
        boolean result = dao.insert(er);

        assertTrue(result);
        assertTrue(er.getId() > 0, "ID must be generated.");

        EscapeRoom fromDb = dao.findById(er.getId());
        assertNotNull(fromDb);
        assertEquals("EscapeRoom 1", fromDb.getName());
    }

    @Test
    void testFindAll() {
        dao.insert(new EscapeRoom("EscapeRoom 1"));
        dao.insert(new EscapeRoom("EscapeRoom 2"));

        var list = dao.findAll();
        assertEquals(2, list.size());
    }

    @Test
    void testFindByIdNotFound() {
        EscapeRoom er = dao.findById(999999);
        assertNull(er);
    }

    @Test
    void testUpdate() {
        EscapeRoom er = new EscapeRoom("Old EscapeRoom");
        dao.insert(er);

        int id = er.getId();

        er.setName("New EscapeRoom");
        boolean result = dao.update(er);

        assertTrue(result);

        EscapeRoom fromDb = dao.findById(id);
        assertEquals("New EscapeRoom", fromDb.getName());
    }

    @Test
    void testDelete() {
        EscapeRoom er = new EscapeRoom("EscapeRoom 12");
        dao.insert(er);

        int id = er.getId();

        boolean result = dao.delete(id);

        assertTrue(result);
        assertNull(dao.findById(id));
    }
}
