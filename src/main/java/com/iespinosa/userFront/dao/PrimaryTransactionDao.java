package com.iespinosa.userFront.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iespinosa.userFront.domain.PrimaryTransaction;

public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long> {
	
	List<PrimaryTransaction> findAll();

}
