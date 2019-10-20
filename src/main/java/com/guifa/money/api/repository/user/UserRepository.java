package com.guifa.money.api.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guifa.money.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	
}
