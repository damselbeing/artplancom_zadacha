package zadacha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import zadacha.entities.Animal;
import zadacha.exceptions.AnimalAlreadyExistsException;
import zadacha.exceptions.AnimalNotFoundException;
import zadacha.exceptions.UserNotFoundException;
import zadacha.services.impl.AnimalServiceImpl;
import java.util.List;

@RestController
public class AnimalController {

    @Autowired
    AnimalServiceImpl animalServiceImpl;

    @GetMapping("/animals")
    public List<Animal> viewAnimals(Authentication authentication)
            throws UserNotFoundException {

        return animalServiceImpl.getAnimals(authentication);
    }

    @GetMapping("/animals/{id}")
    public Animal viewAnimalProfile(@PathVariable(value = "id") Long id)
            throws AnimalNotFoundException {

        return animalServiceImpl.getAnimalById(id);
    }

    @DeleteMapping("/animals/{id}")
    public Integer deleteAnimal(@PathVariable(value = "id") Long id)
            throws AnimalNotFoundException {

        return animalServiceImpl.removeAnimal(id);
    }

    @PutMapping("/animals/{id}")
    public boolean editAnimal(@RequestBody Animal editedAnimal,
                              @PathVariable(value = "id") Long id)
            throws AnimalNotFoundException {

        return animalServiceImpl.updateAnimal(editedAnimal);
    }

    @PostMapping("/animals/add")
    public boolean addNewAnimal(@RequestBody Animal newAnimal)
            throws AnimalAlreadyExistsException {

        return animalServiceImpl.createAnimal(newAnimal);
    }



}
