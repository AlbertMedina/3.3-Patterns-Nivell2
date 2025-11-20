package exceptions;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException() {
        super("Invalid name, please introduce a valid name.");
    }
}
