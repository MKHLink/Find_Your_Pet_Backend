package com.MKHLink.Find_Your_Pet.dtos;

import com.MKHLink.Find_Your_Pet.entities.Animals;
import com.MKHLink.Find_Your_Pet.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaveAnimalRequestDto {
    private UserRequestDto user;
    private Animals animal;
}
