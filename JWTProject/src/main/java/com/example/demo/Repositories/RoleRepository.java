package com.example.demo.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entites.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	Optional<Role> findByName(Role name);
}
