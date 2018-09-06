package com.iespinosa.userFront.dao;

import org.springframework.data.repository.CrudRepository;

import com.iespinosa.userFront.domain.User;

import java.util.List;

public interface UserDao extends CrudRepository<User, Long> {
	
	// Spring scan the substring "findBy" and therefore is aware that we try to retrieve somthing
	// Because we have the "User" class with the "username" property, Spring will handle the operation
	// for us. It is also in charge to analyze the sintax and understand Capitalization
	
	User findByUsername(String username);
	
	User findByEmail(String email);

	List<User> findAll();

}
