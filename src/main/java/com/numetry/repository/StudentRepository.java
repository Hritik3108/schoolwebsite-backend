package com.numetry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.numetry.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student , Integer> {
	Student findByEmail(@Param("email") String email);
}
