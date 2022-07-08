package zadacha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zadacha.entities.Animal;
import zadacha.services.AnimalService;
import java.util.List;

@RestController
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @GetMapping("/animals")
    List<Animal> viewAnimals() {
        return animalService.getAnimals();
    }


}
