package exceptions;

public class EscapeRoomNotFoundException extends NotFoundException {
    public EscapeRoomNotFoundException(String message) {
        super("The escape room with that ID has not been identified. " + message);
    }
}
