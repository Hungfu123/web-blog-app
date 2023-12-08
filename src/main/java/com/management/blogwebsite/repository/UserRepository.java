package com.management.blogwebsite.repository;

import com.management.blogwebsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //Query Methode: sucht die Email der Rolle aus der DB
    User findByEmail(String email);

}
