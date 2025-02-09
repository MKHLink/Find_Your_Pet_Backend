package com.MKHLink.Find_Your_Pet.repositories;

import com.MKHLink.Find_Your_Pet.entities.Animals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animals, Integer> {
    Animals findById(Long id);
}
