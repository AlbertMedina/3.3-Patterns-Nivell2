package exceptions;

public class EscapeRoomNotFoundException extends NotFoundException {
    public EscapeRoomNotFoundException(String message) {
        super("This escape room could not be found. " + message);
    }
}
