package com.MKHLink.Find_Your_Pet.repositories;

import com.MKHLink.Find_Your_Pet.entities.Animals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animals, Integer> {
    //retyrns an animal by its id
    Animals findById(Long id);
}
