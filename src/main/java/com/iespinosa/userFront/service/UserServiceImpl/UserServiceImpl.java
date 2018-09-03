package com.iespinosa.userFront.service.UserServiceImpl;

import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.iespinosa.userFront.dao.RoleDao;
import com.iespinosa.userFront.dao.UserDao;
import com.iespinosa.userFront.domain.User;
import com.iespinosa.userFront.domain.security.UserRole;
import com.iespinosa.userFront.service.AccountService;
import com.iespinosa.userFront.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountService accountService;
	
	public void saveUser(User user) {
		userDao.save(user);
	}
	
	
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	@Transactional
	public User createUser(User user, Set<UserRole> userRoles) {
		User localUser = userDao.findByUsername(user.getUsername());
		
		if (localUser != null) {
			LOG.info("User with username {} already exist. Nothing will be done.", user.getUsername());
		} else {
			String encryptedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			
			for (UserRole userRole: userRoles) {
				roleDao.save(userRole.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			
			user.setPrimaryAccount(accountService.createPrimaryAccount());
			user.setSavingsAccount(accountService.createSavingsAccount());
			
			localUser = userDao.save(user);
		}
		
		return localUser;
	}
	
	public boolean checkIfUserExists(String username, String email) {
		if (checkIfUsernameExists(username) || checkIfEmailExists(email)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkIfUsernameExists(String username) {
		if (findByUsername(username) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkIfEmailExists(String email) {
		if (findByEmail(email) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
