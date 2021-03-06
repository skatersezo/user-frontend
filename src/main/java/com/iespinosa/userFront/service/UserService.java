package com.iespinosa.userFront.service;

import java.util.List;
import java.util.Set;

import com.iespinosa.userFront.domain.User;
import com.iespinosa.userFront.domain.security.UserRole;

public interface UserService {
	
	User findByUsername(String username);
	
	User findByEmail(String email);

	List<User> findUserList();
	
	boolean checkIfUserExists(String username, String email);
	
	boolean checkIfUsernameExists(String username);
	
	boolean checkIfEmailExists(String email);
	
	void saveUser(User user);

	void enableUser(String username);

	void disableUser(String username);
	
	User createUser(User user, Set<UserRole> userRoles);

}
