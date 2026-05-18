package com.interview.microservices.auth.repository;

import com.interview.microservices.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for {@link User} entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by their username.
     *
     * @param username the unique username
     * @return an Optional containing the user if found
     */
    Optional<User> findByUsername(String username);

    /**
     * Check whether a user with the given username already exists.
     *
     * @param username the username to check
     * @return true if the username is already taken
     */
    boolean existsByUsername(String username);

    /**
     * Check whether a user with the given email already exists.
     *
     * @param email the email to check
     * @return true if the email is already registered
     */
    boolean existsByEmail(String email);
}
