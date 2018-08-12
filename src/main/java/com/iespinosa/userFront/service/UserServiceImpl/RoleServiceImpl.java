package com.iespinosa.userFront.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iespinosa.userFront.dao.RoleDao;
import com.iespinosa.userFront.domain.security.Role;
import com.iespinosa.userFront.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	public Role findByName(String name) {
		
		return roleDao.findByName(name);
	}

}
