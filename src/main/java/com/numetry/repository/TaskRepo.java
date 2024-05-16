package com.numetry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.numetry.entity.Task;

public interface TaskRepo extends JpaRepository<Task, String> {

}
