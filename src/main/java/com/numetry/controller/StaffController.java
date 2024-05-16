package com.numetry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.numetry.entity.Staff;
import com.numetry.service.StaffService;

@RestController
@RequestMapping("/api/v1/auth/school")
public class StaffController {

	@Autowired
	private StaffService service;
	
	@PostMapping("/createStaff")
	public ResponseEntity<Staff> createNewStaff(@RequestBody Staff staff ) {
		System.out.println(staff.getName());
		return new ResponseEntity<>(service.createStaff(staff),HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllStaff")
	public ResponseEntity<List<Staff>> getAllStaff(){
		return new ResponseEntity<>(service.getAllStaff(),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteStaff/{staffId}")
	public ResponseEntity<String> deleteStaff(@PathVariable String staffId){
		System.out.println(staffId);
		return new ResponseEntity<>(service.deleteStaff(staffId),HttpStatus.OK);
	} 
	
	@PostMapping("/updateStaff")
	public ResponseEntity<String> updateStaff(@PathVariable String staffId,@RequestBody Staff staff){
		return new ResponseEntity<>(service.updateStaff(staffId, staff),HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/Staff/{staffId}")
	public ResponseEntity<Staff> getStaff(@PathVariable String staffId){
		Staff s=service.staffData(staffId);
		System.out.println(staffId);
		if(s==null) {
			System.out.print("No staff data available");
			return new ResponseEntity<>(s,HttpStatus.NOT_FOUND);
		}
		System.out.println(s.getName());
		return new ResponseEntity<>(s,HttpStatus.OK);
	}
	
}
