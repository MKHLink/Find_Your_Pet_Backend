package com.MKHLink.Find_Your_Pet.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {
    private String email;
    private String password;
}
