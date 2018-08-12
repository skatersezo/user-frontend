package com.iespinosa.userFront.service.UserServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iespinosa.userFront.dao.PrimaryTransactionDao;
import com.iespinosa.userFront.dao.SavingsTransactionDao;
import com.iespinosa.userFront.domain.PrimaryTransaction;
import com.iespinosa.userFront.domain.SavingsTransaction;
import com.iespinosa.userFront.domain.User;
import com.iespinosa.userFront.service.TransactionService;
import com.iespinosa.userFront.service.UserService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PrimaryTransactionDao primaryTransactionDao;
	
	@Autowired
	private SavingsTransactionDao savingsTransactionDao;
	 
	public List<PrimaryTransaction> findPrimaryTransactionList(String username) {
		User user = userService.findByUsername(username);
		
		List<PrimaryTransaction> primaryTransactionList = user.getPrimaryAccount().getPrimaryTransactionList();
		
		return primaryTransactionList;
	}
	
	public List<SavingsTransaction> findSavingsTransactionList(String username) {
		User user = userService.findByUsername(username);
		
		List<SavingsTransaction> savingsTransactionList = user.getSavingsAccount().getSavingTransactionList();
		
		return savingsTransactionList;
	}

	@Override
	public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionDao.save(primaryTransaction);
		
	}

	@Override
	public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
		savingsTransactionDao.save(savingsTransaction);
		
	}
	
	@Override
	public void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionDao.save(primaryTransaction);
		
	}

	@Override
	public void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction) {
		savingsTransactionDao.save(savingsTransaction);
		
	}

}
