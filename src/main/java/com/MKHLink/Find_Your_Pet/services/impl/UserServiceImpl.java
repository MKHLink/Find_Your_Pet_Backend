package com.MKHLink.Find_Your_Pet.services.impl;

import com.MKHLink.Find_Your_Pet.dtos.UserRequestDto;
import com.MKHLink.Find_Your_Pet.dtos.UserResponseDto;
import com.MKHLink.Find_Your_Pet.entities.Animals;
import com.MKHLink.Find_Your_Pet.entities.User;
import com.MKHLink.Find_Your_Pet.repositories.AnimalRepository;
import com.MKHLink.Find_Your_Pet.repositories.UserRepository;
import com.MKHLink.Find_Your_Pet.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.MKHLink.Find_Your_Pet.mappers.UserMapper.dtoToEntity;
import static com.MKHLink.Find_Your_Pet.mappers.UserMapper.entityToDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;

    //Create a user, password hashed before storing in the database
    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        log.info("Registering user: {}", userRequestDto);

        //if the user with the email already exists, calls the login api
        if(userRepository.findByEmail(userRequestDto.getEmail()) != null) {
            log.info("User already exists: {}, logging in!", userRequestDto.getEmail());
            return userLogin(userRequestDto);
        }

        //Creates a new User object
        User userToCreate = dtoToEntity(userRequestDto);
        //saves user in database and turns it into a dto to return
        UserResponseDto createdUser =  entityToDto(userRepository.saveAndFlush(userToCreate));
        log.info("Created user: " + createdUser);
        return createdUser;
    }

    //Logs in an existing user
    @Override
    public UserResponseDto userLogin(UserRequestDto userRequestDto) {
        //finds user by their email
        User userLogin = userRepository.findByEmail(userRequestDto.getEmail());

        if(userLogin == null) {
            log.error("User not found");
        }

        //checks the hashed password in the dd with the password from the request
        if(!BCrypt.checkpw(userRequestDto.getPassword(), userLogin.getPassword())) {
            log.error("Wrong password");
        }

        log.info("Login user: {}", userLogin);
        return entityToDto(userLogin);
    }

    //allows a logged-in user to updates their profile by saving an animal object
    @Override
    public UserResponseDto saveAnimal(UserRequestDto user,Animals animal) {
        //gets the logged-in user
        User findUser = userRepository.findByEmail(user.getEmail());
        //builder pattern to build an animal object
        Animals savedAnimal = Animals.builder()
                .image(animal.getImage())
                .name(animal.getName())
                .species(animal.getSpecies())
                .breed(animal.getBreed())
                .city(animal.getCity())
                .contact(animal.getContact())
                .status(animal.getStatus())
                .build();

        //saves animal in the animal table
        animalRepository.save(savedAnimal);

        //Makes a set from the logged-in user's saved animals
        Set<Animals> saveAnimalToUser = findUser.getSavedAnimals();
        //adds the new animal to the user's set of saved animal
        saveAnimalToUser.add(savedAnimal);
        //updates the user's saved animals set by replacing it with the new animal set
        findUser.setSavedAnimals(saveAnimalToUser);

        return entityToDto(userRepository.saveAndFlush(findUser));
    }

    //soft-deletes an animal from the user's saved animals set
    @Override
    public UserResponseDto deleteAnimal(Long id, UserRequestDto userRequestDto) {
        //get the user
        User findUser = userRepository.findByEmail(userRequestDto.getEmail());
        //get the animal
        Animals animalToDelete = animalRepository.findById(id);
        log.info("ID: {}",id);
        log.info("Deleting animal: {}", animalToDelete);

        //Makes a set from the logged-in user's saved animals
        Set<Animals> deleteAnimalFromUser = findUser.getSavedAnimals();
        //Removes the animal from the users set
        deleteAnimalFromUser.remove(animalToDelete);
        //updates the user's saved animals set by replacing it with the new animal set
        findUser.setSavedAnimals(deleteAnimalFromUser);

        return entityToDto(userRepository.saveAndFlush(findUser));
    }
}
