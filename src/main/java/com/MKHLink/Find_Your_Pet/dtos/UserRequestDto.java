package com.MKHLink.Find_Your_Pet.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {
    //used for logged in user operations
    private String email;
    private String password;
}
