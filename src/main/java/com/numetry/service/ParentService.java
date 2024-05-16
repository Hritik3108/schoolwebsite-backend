package com.numetry.service;

import java.util.List;

import com.numetry.entity.Parent;

public interface ParentService {
	
	public Parent createParent(Parent parent);
	public String deleteParent(String parentId);
	public String updateParent(String parentId,Parent parent);
	public List<Parent> getAllParent();
	Parent parentData(String email);

}
