package zadacha.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zadacha.entities.Animal;
import zadacha.exceptions.AnimalAlreadyExistsException;
import zadacha.exceptions.AnimalNotFoundException;
import zadacha.repositories.AnimalRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    @Autowired
    AnimalRepository animalRepository;

    public List<Animal> getAnimals() {
        return animalRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    public Animal getAnimalById(Long id) throws AnimalNotFoundException {

        return animalRepository
                .findAnimalByIdAnimal(id)
                .orElseThrow(AnimalNotFoundException::new);
    }

    @Transactional
    public Integer removeAnimal(Long id) throws AnimalNotFoundException {

        animalRepository
                .findAnimalByIdAnimal(id)
                .orElseThrow(AnimalNotFoundException::new);

        return animalRepository.deleteAnimalByIdAnimal(id);
    }

    @Transactional
    public boolean saveAnimal(Animal animal) throws AnimalAlreadyExistsException {

        if(animalRepository.existsAnimalByPetName(animal.getPetName())) {
            throw new AnimalAlreadyExistsException();
        }

        animalRepository.save(animal);
        return true;
    }
}
