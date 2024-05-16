package com.numetry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.numetry.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {
	Admin findByEmail(@Param("email") String email);
}
