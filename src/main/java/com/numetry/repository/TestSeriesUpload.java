package com.numetry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.numetry.entity.TestSeries;

public interface TestSeriesUpload extends JpaRepository<TestSeries,String> {

	
}
