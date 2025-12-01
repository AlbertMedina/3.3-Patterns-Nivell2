package exceptions;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super("This user could not be found. " + message);
    }
}
