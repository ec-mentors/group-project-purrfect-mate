package purrfectMate.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Email already registered";

    public EmailAlreadyRegisteredException() {
        super(DEFAULT_MESSAGE);
    }
}
