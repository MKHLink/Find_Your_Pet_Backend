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

    //Returns all exisiting animals in the database
    @Override
    public List<Animals> getAllAnimals() {
        List<Animals> animals = animalRepository.findAll();
        return animals;
    }

    /* deletes an animal by its id, this API call will be made
    periodically in the background to get rid of redundant data
     */
    @Override
    public void deleteAninal(Long id) {
        //get all users
        List<User> users = userRepository.findAll();
        //find the animal to be deleted
        Animals animal = animalRepository.findById(id);
        //flag to see if the animal is being used by a user or not
        boolean flag = false;

        //iterate through all users
        for(User user: users){
            //if the animal is saved by a user flag turns true and is not deleted
            if(user.getSavedAnimals().contains(animal)){
                flag = true;
            }
        }

        //if flag never turns true, delete the animal because it is not being used
        if(!flag){
            animalRepository.delete(animal);
        }else{
            log.info("Animal in use");
        }
    }
}
