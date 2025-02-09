package com.MKHLink.Find_Your_Pet.services.impl;

import com.MKHLink.Find_Your_Pet.entities.Animals;
import com.MKHLink.Find_Your_Pet.entities.User;
import com.MKHLink.Find_Your_Pet.repositories.AnimalRepository;
import com.MKHLink.Find_Your_Pet.repositories.UserRepository;
import com.MKHLink.Find_Your_Pet.services.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final UserRepository userRepository;

    @Override
    public List<Animals> getAllAnimals() {
        List<Animals> animals = animalRepository.findAll();
        return animals;
    }

    @Override
    public void deleteAninal(Long id) {
        List<User> users = userRepository.findAll();
        Animals animal = animalRepository.findById(id);
        boolean flag = false;

        for(User user: users){
            if(user.getSavedAnimals().contains(animal)){
                flag = true;
            }
        }

        if(!flag){
            animalRepository.delete(animal);
        }else{
            log.info("Animal in use");
        }

        log.info("Deleted animal with id: " + id);
    }
}
