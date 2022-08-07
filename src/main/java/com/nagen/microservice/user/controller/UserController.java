package com.nagen.microservice.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagen.microservice.user.dto.UserDepartmentResponseDTO;
import com.nagen.microservice.user.entity.User;
import com.nagen.microservice.user.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public User saveUser(@RequestBody User user) {
		log.info("--------------------Inside saveuser()--------------------------");
		return userService.saveUser(user);
	}

	@GetMapping("/{id}")
	@CircuitBreaker(name = "departmentServiceCircuitBreaker", fallbackMethod = "departmentServiceFallback")
	public Object findUserDetails(@PathVariable("id") Long userId) {
		log.info("--------------------Inside findUserDetails()--------------------------");
		return userService.findUserDetails(userId);
	}
	
	public Object departmentServiceFallback(Exception ex) {
		log.info("Department Service is Down.......Please try after some time");
		return "Department Service is Down.......Please try after some time";
	}

}
