package com.nagen.microservice.user.dto;

import com.nagen.microservice.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDepartmentResponseDTO {

	private User user;
	private Department department;
}
