package ServicesTest;

import decoration.Decoration;
import decoration.DecorationDaoImpl;
import decoration.DecorationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import room.Room;
import room.RoomDaoImpl;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DecorationServiceTest {
    private static DecorationDaoImpl decorationDaoMock;
    private static RoomDaoImpl roomDaoMock;
    private static DecorationService decorationService;

    @BeforeEach
    void setUp() throws Exception {
        decorationDaoMock = mock(DecorationDaoImpl.class);
        roomDaoMock = mock(RoomDaoImpl.class);

        decorationService = new DecorationService(roomDaoMock, decorationDaoMock);
    }

    private void injectMock(String fieldName, Object mock) throws Exception {
        Field field = DecorationService.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(decorationService, mock);
    }

    @Test
    void addDecorationTest() {
        int roomId = 10;

        when(roomDaoMock.findById(roomId)).thenReturn(new Room("Room1", 1,
                17.5, 1));
        when(decorationDaoMock.insert(any(Decoration.class))).thenReturn(true);

        boolean result = decorationService.addDecoration("Lamp", "Wood", 15.5, roomId);

        assertTrue(result);
        verify(roomDaoMock).findById(roomId);
        verify(decorationDaoMock).insert(any(Decoration.class));
    }

    @Test
    void updateDecorationTest() {
        Decoration existing = new Decoration("Lamp", "Wood", 10.0, 1);
        existing.setId(5);

        when(decorationDaoMock.findById(5)).thenReturn(existing);
        when(roomDaoMock.findById(2)).thenReturn(new Room("Room2", 2, 17.5, 1));
        when(decorationDaoMock.update(existing)).thenReturn(true);

        boolean result = decorationService.updateDecoration(5, "New name", "Plastic", 20.0, 2);

        assertTrue(result);
        assertEquals("New name", existing.getName());
        assertEquals("Plastic", existing.getMaterial());
        assertEquals(20.0, existing.getValue());
        assertEquals(2, existing.getRoomId());

        verify(decorationDaoMock).findById(5);
        verify(roomDaoMock).findById(2);
        verify(decorationDaoMock).update(existing);
    }

    @Test
    void deleteDecorationTest() {
        when(decorationDaoMock.delete(5)).thenReturn(true);

        boolean result = decorationService.removeDecoration(5);

        assertTrue(result);
        verify(decorationDaoMock).delete(5);
    }

    @Test
    void testGetAllDecorations() {
        when(decorationDaoMock.findAll()).thenReturn(List.of(
                new Decoration("Lamp", "Wood", 10.0, 1),
                new Decoration("Shelf", "Metal", 20.0, 2)
        ));

        List<Decoration> list = decorationService.getDecorations();

        assertEquals(2, list.size());
        verify(decorationDaoMock).findAll();
    }

    @Test
    void testGetDecorationById() {
        Decoration decoration = new Decoration("Table", "Wood", 30.0, 1);
        decoration.setId(3);

        when(decorationDaoMock.findById(3)).thenReturn(decoration);

        Decoration result = decorationService.getDecorationById(3);

        assertNotNull(result);
        assertEquals("Table", result.getName());
        verify(decorationDaoMock).findById(3);
    }
}
