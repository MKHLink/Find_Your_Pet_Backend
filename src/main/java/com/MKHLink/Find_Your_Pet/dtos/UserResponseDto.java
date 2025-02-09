package com.MKHLink.Find_Your_Pet.dtos;

import com.MKHLink.Find_Your_Pet.entities.Animals;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String email;
    private Set<Animals> animals;
}
