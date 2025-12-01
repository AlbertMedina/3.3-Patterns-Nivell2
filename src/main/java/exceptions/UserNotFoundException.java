package exceptions;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super("We haven't found the user. " + message);
    }
}
