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

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        log.info("Registering user: {}", userRequestDto);
        User userToCreate = dtoToEntity(userRequestDto);
        UserResponseDto createdUser =  entityToDto(userRepository.saveAndFlush(userToCreate));
        log.info("Created user: " + createdUser);
        return createdUser;
    }

    @Override
    public UserResponseDto userLogin(UserRequestDto userRequestDto) {
        User userLogin = userRepository.findByEmail(userRequestDto.getEmail());

        if(userLogin == null) {
            log.error("User not found");
        }

        if(!BCrypt.checkpw(userRequestDto.getPassword(), userLogin.getPassword())) {
            log.error("Wrong password");
        }

        log.info("Login user: {}", userLogin);
        return entityToDto(userLogin);
    }

    @Override
    public UserResponseDto saveAnimal(UserRequestDto user,Animals animal) {
        User findUser = userRepository.findByEmail(user.getEmail());
        Animals savedAnimal = Animals.builder()
                .image(animal.getImage())
                .name(animal.getName())
                .species(animal.getSpecies())
                .breed(animal.getBreed())
                .city(animal.getCity())
                .contact(animal.getContact())
                .status(animal.getStatus())
                .build();

        animalRepository.save(savedAnimal);

        Set<Animals> saveAnimalToUser = findUser.getSavedAnimals();
        saveAnimalToUser.add(savedAnimal);
        findUser.setSavedAnimals(saveAnimalToUser);

        return entityToDto(userRepository.saveAndFlush(findUser));
    }

    @Override
    public UserResponseDto deleteAnimal(Long id, UserRequestDto userRequestDto) {
        User findUser = userRepository.findByEmail(userRequestDto.getEmail());
        Animals animalToDelete = animalRepository.findById(id);
        log.info("ID: {}",id);
        log.info("Deleting animal: {}", animalToDelete);

        Set<Animals> deleteAnimalFromUser = findUser.getSavedAnimals();
        deleteAnimalFromUser.remove(animalToDelete);
        findUser.setSavedAnimals(deleteAnimalFromUser);

        return entityToDto(userRepository.saveAndFlush(findUser));
    }
}
