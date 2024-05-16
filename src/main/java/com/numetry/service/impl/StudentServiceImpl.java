package com.numetry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numetry.repository.StudentRepository;
import com.numetry.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository repo;
	
	@Override
	public String deleteStudent(Integer studentRollNo) {
		// TODO Auto-generated method stub
		System.out.println("delete implementation initiated");
		repo.deleteById(studentRollNo);
		return "student deleted";
	}

}
