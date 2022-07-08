package zadacha.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zadacha.entities.Animal;
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
        return animalRepository.findAnimalByIdAnimal(id)
                .orElseThrow(AnimalNotFoundException::new);
    }

    @Transactional
    public boolean saveAnimal(Animal animal) {
        Animal animalFromDB = animalRepository.getAnimalByPetName(animal.getPetName());

        if (animalFromDB != null) {
            return false;
        }

        animalRepository.save(animal);
        return true;
    }


}
