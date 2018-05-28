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
import org.springframework.web.bind.annotation.RequestMapping;
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
		request.setAttribute("page", "fragments/main");
		
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		log.info("login");
		
		return "login";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(HttpServletRequest request){
		log.info("/dashboard");
		return "dashboard";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin/admin";
	}
	
	@GetMapping("/all-tasks")
	public String allTasks(HttpServletRequest request){
		log.info("all-tasks");
		
		List<Task> findAll = taskService.findAll();
		log.info("tasks findAll : {}", findAll);
		
		request.setAttribute("page", "fragments/jpa/list");
		request.setAttribute("tasks", findAll);
		
		return "index";
	}
	
	@GetMapping("/new-task")
	public String newTask(HttpServletRequest request){
		log.info("new-tasks");
		request.setAttribute("page", "fragments/jpa/view");
		
		return "index";
	}
	
	@PostMapping("/save-task")
	public String saveTask(@ModelAttribute Task task, BindingResult bindingResult, HttpServletRequest request){
		task.setDateCreated(new Date());
		taskService.save(task);
		request.setAttribute("tasks", taskService.findAll());
		request.setAttribute("page", "fragments/jpa/list");
		
		return "index";
	}
	
	@GetMapping("/update-task")
	public String updateTask(@RequestParam int id, HttpServletRequest request){
		log.info("new-tasks");
		
		request.setAttribute("task", taskService.findTask(id));
		request.setAttribute("page", "fragments/jpa/view");
		
		return "index";
	}
	
	@GetMapping("/delete-task")
	public String deleteTask(@RequestParam int id, HttpServletRequest request){
		taskService.delete(id);
		request.setAttribute("tasks", taskService.findAll());
		request.setAttribute("page", "fragments/jpa/list");
		
		return "index";
	}
	
	@GetMapping("/403")
	public String error403() {
		return "error/403";
	}
}
