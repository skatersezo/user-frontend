package com.iespinosa.userFront.service;

import java.util.List;

import com.iespinosa.userFront.domain.PrimaryTransaction;
import com.iespinosa.userFront.domain.SavingsTransaction;

public interface TransactionService {
	
	List<PrimaryTransaction> findPrimaryTransactionList(String username);
	List<SavingsTransaction> findSavingsTransactionList(String username);
	void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);
	void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);
	void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction);
	void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction);

}
