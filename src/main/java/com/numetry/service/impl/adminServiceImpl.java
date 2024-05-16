package com.numetry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numetry.entity.Admin;
import com.numetry.repository.AdminRepository;
import com.numetry.service.AdminService;

@Service
public class adminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository repo;
	
	@Override
	public Admin createParent(Admin admin) {
		// TODO Auto-generated method stub
		return repo.save(admin);
	}

	@Override
	public Admin adminData(String email) {
		// TODO Auto-generated method stub
		Admin admin=repo.findByEmail(email);
		if(admin==null) {
			System.out.println("No admin data avaialable");
			return null;
		}
		return admin;
	}

}
