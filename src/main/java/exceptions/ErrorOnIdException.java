package exceptions;

public class ErrorOnIdException extends RuntimeException {
    public ErrorOnIdException() {
        super("Invalid ID, please introduce an ID that is not negative or  equal to 0.");
    }
}
