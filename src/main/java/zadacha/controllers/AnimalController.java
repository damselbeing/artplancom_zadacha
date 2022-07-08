package zadacha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zadacha.entities.Animal;
import zadacha.exceptions.AnimalAlreadyExistsException;
import zadacha.exceptions.AnimalNotFoundException;
import zadacha.services.AnimalService;
import java.util.List;

@RestController
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @GetMapping("/animals")
    public List<Animal> viewAnimals() {

        return animalService.getAnimals();
    }

    @GetMapping("/animal/{id}")
    public Animal viewAnimalProfile(@PathVariable(value = "id") Long id)
            throws AnimalNotFoundException {

        return animalService.getAnimalById(id);
    }

    @DeleteMapping("/animal/{id}")
    public Integer deleteAnimal(@PathVariable(value = "id") Long id)
            throws AnimalNotFoundException {

        return animalService.removeAnimal(id);
    }

    @PostMapping("/animal")
    public boolean addNewAnimal(@RequestBody Animal newAnimal)
            throws AnimalAlreadyExistsException {

        return animalService.saveAnimal(newAnimal);
    }



}
