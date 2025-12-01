package exceptions;

public class RoomNotFoundException extends NotFoundException {
    public RoomNotFoundException(String message) {
        super("This room could not be found. " + message);
    }
}
