package com.iespinosa.userFront.service;

import com.iespinosa.userFront.domain.security.Role;

public interface RoleService {

	Role findByName(String name);
}
