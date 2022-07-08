package zadacha.exceptions;


public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("Error: the user is not found!");
    }

}
