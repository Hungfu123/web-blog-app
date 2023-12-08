package com.management.blogwebsite.repository;

import com.management.blogwebsite.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    //Query Methode: sucht den Namen der Rolle aus der DB
    Role findByName(String name);
}
