package com.numetry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numetry.entity.Parent;
import com.numetry.repository.ParentRepository;
import com.numetry.service.ParentService;

@Service
public class ParentServiceImpl implements ParentService {

	@Autowired
	private ParentRepository repo;
	
	@Override
	public Parent createParent(Parent parent) {
		// TODO Auto-generated method stub
		return repo.save(parent);
	}

	@Override
	public String deleteParent(String parentId) {
		// TODO Auto-generated method stub
		repo.deleteById(parentId);
		return "Staff with id "+parentId+ "has been deleted";
	}

	@Override
	public String updateParent(String parentId, Parent parent) {
		// TODO Auto-generated method stub
		parent.setParentId(parentId);
		repo.save(parent);
		return "Staff with staff Id "+parentId+" is updated";
	}

	@Override
	public List<Parent> getAllParent() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Parent parentData(String email) {
		// TODO Auto-generated method stub
		Parent parent=repo.findByEmail(email);
		if(parent==null) {
			System.out.println("No parent data avaialable");
			return null;
		}
		return parent;
	}

}
