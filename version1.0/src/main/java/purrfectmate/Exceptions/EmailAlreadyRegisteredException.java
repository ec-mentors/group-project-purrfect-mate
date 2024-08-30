package purrfectmate.Exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Email already registered";

    public EmailAlreadyRegisteredException() {
        super(DEFAULT_MESSAGE);
    }
}
