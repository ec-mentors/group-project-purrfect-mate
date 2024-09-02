package purrfectMate.exceptions;

public class WrongLoginDataException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Username or password is incorrect";

    public WrongLoginDataException() {
        super(DEFAULT_MESSAGE);
    }

}
