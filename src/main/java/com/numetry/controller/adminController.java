package com.numetry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.numetry.entity.Admin;
import com.numetry.service.AdminService;

@RestController
@RequestMapping("/api/v1/auth/school/admin")
public class adminController {
	
	@Autowired
	private AdminService service;

	@PostMapping("/createAdmin")
	public ResponseEntity<Admin> createNewAdmin(@RequestBody Admin admin ) {
		return new ResponseEntity<>(service.createParent(admin),HttpStatus.CREATED);
	}
	
	@GetMapping("/{adminId}")
	public ResponseEntity<Admin> getAdmin(@PathVariable String adminId){
		System.out.println("Admin data function called");
		Admin a=service.adminData(adminId);
		System.out.println(adminId);
		if(a==null) {
			System.out.print("No admin data available");
			return new ResponseEntity<>(a,HttpStatus.NOT_FOUND);
		}
		System.out.println(a.getName());
		return new ResponseEntity<>(a,HttpStatus.OK);
	}
}
