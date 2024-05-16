package com.numetry.service;

import java.util.List;

import com.numetry.entity.Staff;

public interface StaffService {

	public Staff createStaff(Staff staff);
	public String deleteStaff(String staffId);
	public String updateStaff(String staffId,Staff staff);
	public List<Staff> getAllStaff();
	Staff staffData(String email);
	
}
