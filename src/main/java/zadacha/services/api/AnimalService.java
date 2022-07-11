package zadacha.services.api;

import org.springframework.security.core.Authentication;
import zadacha.entities.Animal;
import zadacha.exceptions.AnimalAlreadyExistsException;
import zadacha.exceptions.AnimalNotFoundException;
import zadacha.exceptions.UserNotFoundException;

import java.util.List;

public interface AnimalService {

    List<Animal> getAnimals(Authentication authentication) throws UserNotFoundException;
    Animal getAnimalById(Long id) throws AnimalNotFoundException;
    Integer removeAnimal(Long id) throws AnimalNotFoundException;
    boolean createAnimal(Animal newAnimal) throws AnimalAlreadyExistsException;
    boolean updateAnimal(Animal editedAnimal) throws AnimalNotFoundException;
}
