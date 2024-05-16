package com.numetry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numetry.entity.Staff;
import com.numetry.repository.StaffRepository;
import com.numetry.service.StaffService;
@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository repo;
	
	@Override
	public Staff createStaff(Staff staff) {
		return repo.save(staff);
	}

	@Override
	public String deleteStaff(String staffId) {
		repo.deleteById(staffId);
		return "Staff with id "+staffId+ "has been deleted";
	}

	@Override
	public String updateStaff(String staffId,Staff staff) {
		staff.setStaffId(staffId);
		repo.save(staff);
		return "Staff with staff Id "+staffId+" is updated";
	}

	@Override
	public List<Staff> getAllStaff() {
		return repo.findAll();
	}
	
	@Override
	public Staff staffData(String email) {
		Staff staff=repo.findByEmail(email);
		if(staff==null) {
			System.out.println("No staff data avaialable");
			return null;
		}
		return staff;
	}

	

}
