package com.example.demo.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entites.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	//Optional<User> findByUsername(String username);

	//Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);
}
