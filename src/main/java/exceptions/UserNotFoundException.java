package exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super("We haven't found the user. Please make sure the user ID is correct.");
    }
}
