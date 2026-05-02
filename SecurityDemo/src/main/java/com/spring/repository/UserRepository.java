package com.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.CustomUser;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long> {

	Optional<CustomUser> findByUsername(String username);

}
