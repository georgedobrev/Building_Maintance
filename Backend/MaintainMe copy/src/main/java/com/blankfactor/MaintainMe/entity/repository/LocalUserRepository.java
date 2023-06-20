package com.blankfactor.MaintainMe.entity.repository;

import com.blankfactor.MaintainMe.entity.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
//repository

//creates CRUD Repository for the user with all sorts of methods and since my user has an ID
// of type Long I pass the Identifier as Long
public interface LocalUserRepository extends ListCrudRepository<User,Long> {
    Optional<User> findByUsernameIgnoreCase(String username);

    Optional<User> findByEmailIgnoreCase(String email);


}
