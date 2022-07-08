package zadacha.exceptions;

public class AnimalAlreadyExistsException extends Exception{

    public AnimalAlreadyExistsException() {

        super("Error: the animal already exists!");
    }
}
