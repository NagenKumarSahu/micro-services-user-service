package com.nagen.microservice.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagen.microservice.user.dto.Department;
import com.nagen.microservice.user.dto.UserDepartmentResponseDTO;
import com.nagen.microservice.user.entity.User;
import com.nagen.microservice.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	public User saveUser(User user) {
		log.info("******************Inside saveuser()******************");
		return userRepository.save(user);
	}

	public UserDepartmentResponseDTO findUserDetails(Long userId) {
		log.info("******************Inside findUserDetails()******************");
		UserDepartmentResponseDTO userDepartmentResponseDTO = new UserDepartmentResponseDTO();
		User user = userRepository.findByUserId(userId);
		
		Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),
				Department.class);

		userDepartmentResponseDTO.setUser(user);
		userDepartmentResponseDTO.setDepartment(department);

		return userDepartmentResponseDTO;
	}
}
