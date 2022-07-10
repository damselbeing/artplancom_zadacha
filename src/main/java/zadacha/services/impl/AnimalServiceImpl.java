package zadacha.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zadacha.entities.Animal;
import zadacha.exceptions.AnimalAlreadyExistsException;
import zadacha.exceptions.AnimalNotFoundException;
import zadacha.repositories.AnimalRepository;
import zadacha.services.api.AnimalService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Transactional
    public List<Animal> getAnimals() {
        return animalRepository.findAll().stream().collect(Collectors.toList());
    }

    @Transactional
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
    public boolean createAnimal(Animal newAnimal) throws AnimalAlreadyExistsException {

        if(animalRepository.existsAnimalByPetName(newAnimal.getPetName())) {
            throw new AnimalAlreadyExistsException();
        }

        animalRepository.save(newAnimal);
        return true;
    }

    @Transactional
    public boolean updateAnimal(Animal editedAnimal) throws AnimalNotFoundException {

        Animal animalFromDB = animalRepository
                .findAnimalByIdAnimal(editedAnimal.getIdAnimal())
                .orElseThrow(AnimalNotFoundException::new);

        animalFromDB.setIdAnimal(editedAnimal.getIdAnimal());
        animalFromDB.setSpecies(editedAnimal.getSpecies());
        animalFromDB.setBirthDate(editedAnimal.getBirthDate());
        animalFromDB.setSex(editedAnimal.getSex());
        animalFromDB.setPetName(editedAnimal.getPetName());

        animalRepository.save(editedAnimal);
        return true;
    }
}
