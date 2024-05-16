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

import com.numetry.entity.Notice;
import com.numetry.entity.Task;
import com.numetry.service.HeadLineService;
import com.numetry.service.TaskService;

@RestController
@RequestMapping("/api/v1/auth/school")
public class TaskAndHeadlineController {

	@Autowired
	private HeadLineService headlineService;
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping("/createTask")
	public ResponseEntity<Task> createNewTask(@RequestBody Task task ) {
		return new ResponseEntity<>(taskService.createTask(task),HttpStatus.CREATED);
	}
	
	@PostMapping("/createNotice")
	public ResponseEntity<Notice> createNewNotice(@RequestBody Notice notice ) {
		System.out.println(notice.getNotice());
		return new ResponseEntity<>(headlineService.createNotice(notice),HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllTask")
	public ResponseEntity<List<Task>> getAllTask(){
		return new ResponseEntity<>(taskService.getAllTask(),HttpStatus.OK);
	}
	
	@GetMapping("/getAllNotice")
	public ResponseEntity<List<Notice>> getAllStaff(){
		return new ResponseEntity<>(headlineService.getAllNotice(),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteTask/{taskId}")
	public ResponseEntity<String> deleteTask(@PathVariable String taskId){
		return new ResponseEntity<>(taskService.deleteTask(taskId),HttpStatus.OK);
	} 
	
	@DeleteMapping("/deleteNotice/{noticeId}")
	public ResponseEntity<String> deleteStaff(@PathVariable String noticeId){
		return new ResponseEntity<>(headlineService.deleteNotice(noticeId),HttpStatus.OK);
	} 
	
	@PostMapping("/updateTask")
	public ResponseEntity<String> updateTask(@PathVariable String taskId,@RequestBody Task task){
		return new ResponseEntity<>(taskService.updateTask(taskId, task),HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/updateNotice")
	public ResponseEntity<String> updateStaff(@PathVariable String noticeId,@RequestBody Notice notice){
		return new ResponseEntity<>(headlineService.updateNotice(noticeId, notice),HttpStatus.ACCEPTED);
	}
	
}
