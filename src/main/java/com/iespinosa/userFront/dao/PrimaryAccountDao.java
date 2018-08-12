package com.iespinosa.userFront.dao;

import org.springframework.data.repository.CrudRepository;

import com.iespinosa.userFront.domain.PrimaryAccount;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long> {
	
	PrimaryAccount findByAccountNumber(int accountNumber);

}
