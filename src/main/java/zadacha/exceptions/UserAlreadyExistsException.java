package zadacha.exceptions;

public class UserAlreadyExistsException extends Exception{

    public UserAlreadyExistsException() {

        super("Error: the user already exists!");
    }
}
