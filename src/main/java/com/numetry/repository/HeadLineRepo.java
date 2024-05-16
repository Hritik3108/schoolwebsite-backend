package com.numetry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.numetry.entity.Notice;

public interface HeadLineRepo extends JpaRepository<Notice, String> {

}
