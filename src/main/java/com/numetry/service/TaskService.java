package com.numetry.service;

import java.util.List;

import com.numetry.entity.Task;

public interface TaskService {
	
	public Task createTask(Task task);
	public String deleteTask(String taskId);
	public String updateTask(String taskId,Task task);
	public List<Task> getAllTask();

}
