package com.MKHLink.Find_Your_Pet.services;

import com.MKHLink.Find_Your_Pet.entities.Animals;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnimalService {
    //AnimalServiceImpl implements this interface

    List<Animals> getAllAnimals();

    void deleteAninal(Long id);
}
