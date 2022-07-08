package zadacha.exceptions;

public class AnimalNotFoundException extends Exception{

    public AnimalNotFoundException() {

        super("Error: the animal is not found!");
    }
}
