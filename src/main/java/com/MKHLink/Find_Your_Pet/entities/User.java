package com.MKHLink.Find_Your_Pet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name="user_table")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Animals> savedAnimals;
}
