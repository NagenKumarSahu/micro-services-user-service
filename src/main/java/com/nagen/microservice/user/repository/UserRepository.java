package com.nagen.microservice.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagen.microservice.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserId(Long userId);

}
