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

import com.numetry.entity.Parent;
import com.numetry.service.ParentService;

@RestController
@RequestMapping("/api/v1/auth/school/parent")
public class ParentController {
	
	@Autowired
	private ParentService service;
	
	@PostMapping("/createParent")
	public ResponseEntity<Parent> createNewParent(@RequestBody Parent parent ) {
		System.out.println(parent.getName());
		return new ResponseEntity<>(service.createParent(parent),HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllParent")
	public ResponseEntity<List<Parent>> getAllParent(){
		return new ResponseEntity<>(service.getAllParent(),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteParent/{parentId}")
	public ResponseEntity<String> deleteParent(@PathVariable String parentId){
		return new ResponseEntity<>(service.deleteParent(parentId),HttpStatus.OK);
	} 
	
	@PostMapping("/updateParent")
	public ResponseEntity<String> updateParent(@PathVariable String parentId,@RequestBody Parent parent){
		return new ResponseEntity<>(service.updateParent(parentId, parent),HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/{parentId}")
	public ResponseEntity<Parent> getParent(@PathVariable String parentId){
		System.out.println("Parent data function called");
		Parent p=service.parentData(parentId);
		System.out.println(parentId);
		if(p==null) {
			System.out.print("No parent data available");
			return new ResponseEntity<>(p,HttpStatus.NOT_FOUND);
		}
		System.out.println(p.getName());
		return new ResponseEntity<>(p,HttpStatus.OK);
	}

}
