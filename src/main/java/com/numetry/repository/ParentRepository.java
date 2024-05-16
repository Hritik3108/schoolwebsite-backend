package com.numetry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.numetry.entity.Parent;

public interface ParentRepository extends JpaRepository<Parent, String> {
	Parent findByEmail(@Param("email") String email);
}
