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

    @GetMapping("/animals/{id}")
    public Animal viewAnimalProfile(@PathVariable(value = "id") Long id)
            throws AnimalNotFoundException {

        return animalService.getAnimalById(id);
    }

    @DeleteMapping("/animals/{id}")
    public Integer deleteAnimal(@PathVariable(value = "id") Long id)
            throws AnimalNotFoundException {

        return animalService.removeAnimal(id);
    }

    @PutMapping("/animals/{id}")
    public boolean editAnimal(@RequestBody Animal editedAnimal,
                              @PathVariable(value = "id") Long id)
            throws AnimalNotFoundException {

        return animalService.updateAnimal(editedAnimal);
    }

    @PostMapping("/animals/add")
    public boolean addNewAnimal(@RequestBody Animal newAnimal)
            throws AnimalAlreadyExistsException {

        return animalService.createAnimal(newAnimal);
    }



}
