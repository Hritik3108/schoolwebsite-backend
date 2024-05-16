package com.numetry.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
	@Id
	private String rollno;
	private String name;
	private String address;
	private String std;
	private String email;
	private String mobno;
	
	
	
	
	
	public Student() {
		
	}
	
	public Student(String rollno, String name, String address, String std, String email, String mobno) {
		super();
		this.rollno = rollno;
		this.name = name;
		this.address = address;
		this.std = std;
		this.email = email;
		this.mobno = mobno;
	}
	public String getRollno() {
		return rollno;
	}
	public void setRollno(String rollno) {
		this.rollno = rollno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStd() {
		return std;
	}
	public void setStd(String std) {
		this.std = std;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobno() {
		return mobno;
	}
	public void setMobno(String mobno) {
		this.mobno = mobno;
	}

	@Override
	public String toString() {
		return "Student [rollno=" + rollno + ", name=" + name + ", address=" + address + ", std=" + std + ", email="
				+ email + ", mobno=" + mobno + "]";
	}
	

}
