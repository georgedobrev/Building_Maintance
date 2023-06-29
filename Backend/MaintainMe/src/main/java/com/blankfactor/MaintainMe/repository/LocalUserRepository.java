package com.blankfactor.MaintainMe.repository;

import com.blankfactor.MaintainMe.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
//repository

//creates CRUD Repository for the user with all sorts of methods and since my user has an ID
// of type Long I pass the Identifier as Long
public interface LocalUserRepository extends ListCrudRepository<User,Long> {
    Optional<User> findByUsernameIgnoreCase(String username);

    Optional<User> findByEmailIgnoreCase(String email);
    @Query(value = "SELECT * FROM User u WHERE u.email = :email", nativeQuery = true)
    User getUserByEmail(@Param("email") String email);

    @Query(value = "select * from user where id = :userId", nativeQuery = true)
    User getUserById(@Param("userId") Long userID);




}
