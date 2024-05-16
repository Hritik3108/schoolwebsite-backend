package com.numetry.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.numetry.entity.User;

public interface SchoolWebsiteRepo extends JpaRepository<User, Long> {

	Optional<User> findByEmailAndPassword(String email, String password);
	User findByEmail(String email);
}
