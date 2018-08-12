package com.iespinosa.userFront.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iespinosa.userFront.domain.SavingsTransaction;

public interface SavingsTransactionDao extends CrudRepository<SavingsTransaction, Long> {
	
	List<SavingsTransaction> findAll();

}
