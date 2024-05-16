package com.numetry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.numetry.entity.UploadedFile;

@Repository
public interface FileUploadRepository extends JpaRepository<UploadedFile,String> {
}