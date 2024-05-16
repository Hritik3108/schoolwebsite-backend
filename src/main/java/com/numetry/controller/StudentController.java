package com.numetry.controller;

import java.security.Provider.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.numetry.entity.Student;
import com.numetry.repository.StudentRepository;
import com.numetry.service.StudentService;

@RestController
@RequestMapping("/api/v1/auth/school")
public class StudentController {
	
	@Autowired
	private StudentRepository  studentRepository;
	
	@Autowired
	private StudentService service;
	
	@PostMapping("/saveStudent")
	public Student saveData(@RequestBody Student student) {
		System.out.println(student.getName());
		studentRepository.save(student);
		return student;
	}
	//handler for fetch a single record
	@GetMapping("/getStudent/{rollNo}")
	public Student getStudentData(@PathVariable int rollNo) {
		List<Student> ls=studentRepository.findAll();
		Student sRequired;
		for(Student s:ls) {
			int roll=Integer.parseInt(s.getRollno());
			if(roll==rollNo) {
				return s;
			}
		}
		return null;	
	}
	
	//handler for fetch all  data from DB
	@GetMapping("/getAllStudent")
	public List<Student> getAll()
	{
		List<Student> studentList= studentRepository.findAll();
		return studentList;
	}
	
//	@DeleteMapping("/deleteStudent/{rollno}")
//	public ResponseEntity<String> deleteStudent(@PathVariable String rollno){
//		Integer roll=Integer.parseInt(rollno);
//		System.out.println("delete api called for roll number:"+roll);
//		return new ResponseEntity<>(service.deleteStudent(roll),HttpStatus.OK);
//	} 
	
	//handler for delete a particular record DB
	@DeleteMapping("/deleteStudent/{rollNo}")
	public String deleteStudent(@PathVariable int rollNo) {
		System.out.println(rollNo);
		List<Student> ls=studentRepository.findAll();
		System.out.println("ls len="+ls.size());
		for(Student s:ls) {
			int x=Integer.parseInt(s.getRollno());
			System.out.println(x+" "+rollNo);
			if(x==rollNo) {
				System.out.println("roll matched");
				studentRepository.delete(s);
				return "Deleted Successfully!!";
			}
		}
		System.out.println("Roll Number didn't match");
		return "Deletion Failed!!";
	}
	
	//handler for updating a particular record
	@PutMapping("/updateData")
	public Student updateStudentData(@RequestBody Student student)
	{
		System.out.println(student.getName());
		Student s=studentRepository.findByEmail(student.getEmail());
		s.setAddress(student.getAddress());
		s.setMobno(student.getMobno());
		s.setName(student.getName());
		s.setStd(student.getStd());
		studentRepository.save(s);
		return student;
	}
	
	
	@GetMapping("/student/{email}")
	public ResponseEntity<Student> getStudentData(@PathVariable String email) {
//		System.out.println(email);
		List<Student> ls=studentRepository.findAll();
		Student sRequired;
		for(Student s:ls) {
			if(s.getEmail().equals(email)) {
				return new ResponseEntity<>(s,HttpStatus.OK);
			}
		}
		System.out.println("no student found");
		return new ResponseEntity<>(null,HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}

}
