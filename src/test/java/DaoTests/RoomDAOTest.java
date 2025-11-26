package DaoTests;

import db.DBConnection;
import escapeRoom.EscapeRoom;
import escapeRoom.EscapeRoomDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import room.Difficulty;
import room.Room;
import room.RoomDaoImpl;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomDAOTest {

    private int escapeRoomID;
    private static RoomDaoImpl dao;

    @BeforeEach
    void setUp() throws Exception {
        dao = new RoomDaoImpl();

        Connection conn = DBConnection.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute("DELETE FROM escape_room");
        stmt.execute("DELETE FROM room");
        stmt.execute("ALTER TABLE room AUTO_INCREMENT = 1");
        stmt.execute("ALTER TABLE escape_room AUTO_INCREMENT = 1");

        EscapeRoomDaoImpl dao1 = new EscapeRoomDaoImpl();
        dao1.insert(new EscapeRoom("EscapeRoom52"));


    }

    @Test
    void testInsert() {
        Room room = new Room("Indiana Jones", Difficulty.MEDIUM, 15.5, 1);
        boolean result = dao.insert(room);

        assertTrue(result);
        assertTrue(room.getId() > 0, "ID must be generated.");

        Room fromDb = dao.findById(room.getId());
        assertNotNull(fromDb);
        assertEquals("Indiana Jones", fromDb.getName());
    }

    @Test
    void testFindAll() {
        dao.insert(new Room("Room 1", Difficulty.EASY,19.5,1));
        dao.insert(new Room("Room 2", Difficulty.HARD, 25.45, 1));

        var list = dao.findAll();
        assertEquals(2, list.size());
    }

    @Test
    void testFindByIdNotFound() {
        Room room = dao.findById(999999);
        assertNull(room);
    }

    @Test
    void testUpdate() {
        Room room = new Room("Old Room", Difficulty.EASY, 10.50, 1);
        dao.insert(room);

        int id = room.getId();

        room.setName("New Room");
        boolean result = dao.update(room);

        assertTrue(result);

        Room fromDb = dao.findById(id);
        assertEquals("New Room", fromDb.getName());
    }

    @Test
    void testDelete() {
        Room room = new Room("Room 12", Difficulty.HARD, 28.45, 1);
        dao.insert(room);

        int id = room.getId();

        boolean result = dao.delete(id);

        assertTrue(result);
        assertNull(dao.findById(id));
    }
}

