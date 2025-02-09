package com.MKHLink.Find_Your_Pet.dtos;

import com.MKHLink.Find_Your_Pet.entities.Animals;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaveAnimalRequestDto {
    //used for deleting animal as a user
    private UserRequestDto user;
    private Animals animal;
}
