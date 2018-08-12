package com.iespinosa.userFront.dao;

import org.springframework.data.repository.CrudRepository;

import com.iespinosa.userFront.domain.SavingsAccount;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long> {
	
	SavingsAccount findByAccountNumber(int accountNumber);

}
 