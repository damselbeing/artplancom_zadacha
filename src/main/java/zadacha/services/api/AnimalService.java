package zadacha.services.api;

import zadacha.entities.Animal;
import zadacha.exceptions.AnimalAlreadyExistsException;
import zadacha.exceptions.AnimalNotFoundException;

import java.util.List;

public interface AnimalService {

    List<Animal> getAnimals();
    Animal getAnimalById(Long id) throws AnimalNotFoundException;
    Integer removeAnimal(Long id) throws AnimalNotFoundException;
    boolean createAnimal(Animal newAnimal) throws AnimalAlreadyExistsException;
    boolean updateAnimal(Animal editedAnimal) throws AnimalNotFoundException;
}
