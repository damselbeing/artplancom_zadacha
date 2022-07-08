package zadacha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zadacha.entities.Animal;
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
    public Animal viewAnimalProfile(
            @PathVariable(value = "id") Long id)
            throws AnimalNotFoundException {
        return animalService.getAnimalById(id);
    }

}
