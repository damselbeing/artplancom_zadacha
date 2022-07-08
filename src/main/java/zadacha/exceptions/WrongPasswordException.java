package zadacha.exceptions;


public class WrongPasswordException extends Exception {

    public WrongPasswordException() {
        super("Error: the password is wrong!");
    }

}
