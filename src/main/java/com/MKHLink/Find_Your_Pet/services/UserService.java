package com.MKHLink.Find_Your_Pet.services;

import com.MKHLink.Find_Your_Pet.dtos.UserRequestDto;
import com.MKHLink.Find_Your_Pet.dtos.UserResponseDto;
import com.MKHLink.Find_Your_Pet.entities.Animals;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserResponseDto registerUser(UserRequestDto userRequestDto);

    UserResponseDto userLogin(UserRequestDto userRequestDto);

    UserResponseDto saveAnimal(UserRequestDto user, Animals animal);

    UserResponseDto deleteAnimal(Long id,UserRequestDto userRequestDto);
}
