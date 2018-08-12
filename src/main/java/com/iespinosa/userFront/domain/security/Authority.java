package com.iespinosa.userFront.domain.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author Ivan
 *
 */
public class Authority implements GrantedAuthority {
	
	private final String authority;
	
	public Authority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}

}
