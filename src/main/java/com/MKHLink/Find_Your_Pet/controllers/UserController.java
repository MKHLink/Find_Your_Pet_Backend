package com.MKHLink.Find_Your_Pet.controllers;

import com.MKHLink.Find_Your_Pet.dtos.SaveAnimalRequestDto;
import com.MKHLink.Find_Your_Pet.dtos.UserRequestDto;
import com.MKHLink.Find_Your_Pet.dtos.UserResponseDto;
import com.MKHLink.Find_Your_Pet.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    //registers a use
    @PostMapping("/register")
    public UserResponseDto registerUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.registerUser(userRequestDto);
    }

    //logs in existing user
    @PostMapping("/login")
    public UserResponseDto loginUser(@RequestBody UserRequestDto userRequestDto) {
       return userService.userLogin(userRequestDto);
    }

    //saves an animal while logged in
    @PutMapping("/save-animal")
    public UserResponseDto saveAnimal(@RequestBody SaveAnimalRequestDto userAndAnimal){
        return userService.saveAnimal(userAndAnimal.getUser(), userAndAnimal.getAnimal());
    }

    //deletes an animal by its id
    @DeleteMapping("/delete/{id}")
    public UserResponseDto deleteAnimal(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){
        return userService.deleteAnimal(id,userRequestDto);
    }
}
