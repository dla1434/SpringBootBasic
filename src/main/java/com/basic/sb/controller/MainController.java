package com.basic.sb.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.basic.sb.model.Task;
import com.basic.sb.service.TaskService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {
	
	@Autowired
	private TaskService taskService;

	@GetMapping("/")
	public String home(HttpServletRequest request){
		log.info("/");
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(HttpServletRequest request){
		log.info("/dashboard");
		return "dashboard";
	}
	
	@GetMapping("/all-tasks")
	public String allTasks(HttpServletRequest request){
		log.info("all-tasks");
		
		List<Task> findAll = taskService.findAll();
		log.info("tasks findAll : {}", findAll);
		
		request.setAttribute("tasks", findAll);
		
		return "jpa/list";
	}
	
	@GetMapping("/new-task")
	public String newTask(HttpServletRequest request){
		log.info("new-tasks");
		
		return "jpa/view";
	}
	
	@PostMapping("/save-task")
	public String saveTask(@ModelAttribute Task task, BindingResult bindingResult, HttpServletRequest request){
		task.setDateCreated(new Date());
		taskService.save(task);
		request.setAttribute("tasks", taskService.findAll());
		
		return "jpa/list";
	}
	
	@GetMapping("/update-task")
	public String updateTask(@RequestParam int id, HttpServletRequest request){
		log.info("new-tasks");
		
		request.setAttribute("task", taskService.findTask(id));
		
		return "jpa/view";
	}
	
	@GetMapping("/delete-task")
	public String deleteTask(@RequestParam int id, HttpServletRequest request){
		taskService.delete(id);
		request.setAttribute("tasks", taskService.findAll());
		
		return "jpa/list";
	}
}
