package com.MKHLink.Find_Your_Pet.controllers;

import com.MKHLink.Find_Your_Pet.entities.Animals;
import com.MKHLink.Find_Your_Pet.services.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/animal")
public class AnimalController {
    /*FOR ADMIN USE ONLY */
    private final AnimalService animalService;

    //Reads all animals from datatbase
    @GetMapping
    public List<Animals> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    //deletes an animal by its id
    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Long id) {
        animalService.deleteAninal(id);
    }
}
