package com.MKHLink.Find_Your_Pet.repositories;

import com.MKHLink.Find_Your_Pet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //returns a user by their email
    User findByEmail(String email);
}
