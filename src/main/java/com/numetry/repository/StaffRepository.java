package com.numetry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.numetry.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, String> {
	Staff findByEmail(@Param("email") String email);
}
