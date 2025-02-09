package com.MKHLink.Find_Your_Pet.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(access = AccessLevel.PUBLIC)
@Table(name="animals_table")
public class Animals {

    @Id
    @GeneratedValue
    private Long id;

    private String image;
    private String name;
    private String species;
    private String breed;
    private String city;
    private String contact;
    private String status;
}
