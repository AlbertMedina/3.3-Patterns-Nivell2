package exceptions;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String message) {
        super("This room could not be found, please try again later.");
    }
}
