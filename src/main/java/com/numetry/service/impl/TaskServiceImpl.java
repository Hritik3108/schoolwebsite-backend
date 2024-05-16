package com.numetry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numetry.entity.Task;
import com.numetry.repository.TaskRepo;
import com.numetry.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepo repo;
	
	@Override
	public Task createTask(Task task) {
		// TODO Auto-generated method stub
		return repo.save(task);
	}

	@Override
	public String deleteTask(String taskId) {
		// TODO Auto-generated method stub
		repo.deleteById(taskId);
		return "task with task Id "+taskId+" is deleted";
	}

	@Override
	public String updateTask(String taskId, Task task) {
		// TODO Auto-generated method stub
		task.setTaskId(taskId);
		repo.save(task);
		return "task with task Id "+taskId+" is updated";
	}

	@Override
	public List<Task> getAllTask() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
