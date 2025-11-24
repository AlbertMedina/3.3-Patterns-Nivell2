import static org.junit.jupiter.api.Assertions.*;

import hint.Hint;
import hint.HintDaoImpl;
import db.DBConnection;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HintDaoImplTest {

    private HintDaoImpl hintDao;

    @BeforeEach
    public void setup() {
        hintDao = new HintDaoImpl();
    }

    @Test
    public void testInsertAndFindById() {
        Hint hint = new Hint("Test text", "Test theme", 10.0, 1);
        boolean inserted = hintDao.insert(hint);
        assertTrue(inserted);
        assertTrue(hint.getId() > 0);

        Hint fetched = hintDao.findById(hint.getId());
        assertNotNull(fetched);
        assertEquals("Test text", fetched.getText());
        assertEquals("Test theme", fetched.getTheme());
    }

    @Test
    public void testFindAll() {
        List<Hint> allHints = hintDao.findAll();
        assertNotNull(allHints);

    }

    @Test
    public void testUpdate() {
        Hint hint = new Hint("Old text", "Old theme", 5.0, 1);
        hintDao.insert(hint);

        hint.setText("New text");
        hint.setTheme("New theme");
        hint.setValue(15.0);

        boolean updated = hintDao.update(hint);
        assertTrue(updated);

        Hint updatedHint = hintDao.findById(hint.getId());
        assertEquals("New text", updatedHint.getText());
        assertEquals("New theme", updatedHint.getTheme());
        assertEquals(15.0, updatedHint.getValue());
    }

    @Test
    public void testDelete() {

        Hint hint = new Hint("Delete text", "Delete theme", 1.0, 1);
        hintDao.insert(hint);

        boolean deleted = hintDao.delete(hint.getId());
        assertTrue(deleted);

        Hint shouldBeNull = hintDao.findById(hint.getId());
        assertNull(shouldBeNull);
    }

    @Test
    void testConnection() {

        try (Connection connection = DBConnection.getInstance().getConnection()) {
            assertNotNull(connection);
            assertFalse(connection.isClosed());
        } catch (SQLException e) {
            fail("No se pudo conectar a la base de datos: " + e.getMessage());
        }
    }
}


