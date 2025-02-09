package com.MKHLink.Find_Your_Pet.mappers;

import com.MKHLink.Find_Your_Pet.dtos.UserRequestDto;
import com.MKHLink.Find_Your_Pet.dtos.UserResponseDto;
import com.MKHLink.Find_Your_Pet.entities.User;

public class UserMapper {
    public static UserResponseDto entityToDto(User user){
        return new UserResponseDto(user.getEmail(), user.getSavedAnimals());
    };

    public static User dtoToEntity(UserRequestDto userRequestDto){
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        return user;
    };
}
