package com.numetry.service;


import com.numetry.entity.Admin;

public interface AdminService {

	public Admin createParent(Admin admin);
	Admin adminData(String email);
}
