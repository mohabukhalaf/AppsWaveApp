package com.example.appswave.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.appswave.demo.domain.entities.User;

public interface IUserRepository extends JpaRepositoryImplementation<User, Long> {

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

}
