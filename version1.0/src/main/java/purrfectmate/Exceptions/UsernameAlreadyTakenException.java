package purrfectmate.Exceptions;

public class UsernameAlreadyTakenException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Username already taken";

    public UsernameAlreadyTakenException() {
        super(DEFAULT_MESSAGE);
    }
}
