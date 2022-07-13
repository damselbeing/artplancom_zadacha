package zadacha.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zadacha.entities.Animal;
import zadacha.entities.User;
import zadacha.exceptions.AnimalAlreadyExistsException;
import zadacha.exceptions.AnimalNotFoundException;
import zadacha.exceptions.UserNotFoundException;
import zadacha.repositories.AnimalRepository;
import zadacha.repositories.UserRepository;
import zadacha.services.api.AnimalService;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<Animal> getAnimals(Authentication authentication)
            throws UserNotFoundException{

        User user = userRepository.findUserByName(authentication.getName())
                .orElseThrow(UserNotFoundException::new);

        return user.getAnimals();
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
        animalFromDB.setUser(editedAnimal.getUser());

        animalRepository.save(editedAnimal);
        return true;
    }
}
