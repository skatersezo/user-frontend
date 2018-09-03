package com.iespinosa.userFront.service.UserServiceImpl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iespinosa.userFront.dao.PrimaryAccountDao;
import com.iespinosa.userFront.dao.SavingsAccountDao;
import com.iespinosa.userFront.domain.PrimaryAccount;
import com.iespinosa.userFront.domain.PrimaryTransaction;
import com.iespinosa.userFront.domain.SavingsAccount;
import com.iespinosa.userFront.domain.SavingsTransaction;
import com.iespinosa.userFront.domain.User;
import com.iespinosa.userFront.service.AccountService;
import com.iespinosa.userFront.service.TransactionService;
import com.iespinosa.userFront.service.UserService;

@Service
public class AccountServiceImpl implements AccountService {
	
	// random number
	private static int nextAccountNumber = 11123435;
	
	@Autowired
	private PrimaryAccountDao primaryAccountDao;
	
	@Autowired
	private SavingsAccountDao savingsAccountDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;

	private int accountGen() {
		return ++nextAccountNumber;
	}
	
	public PrimaryAccount createPrimaryAccount() {
		PrimaryAccount primaryAccount = new PrimaryAccount();
		primaryAccount.setAccountBalance(new BigDecimal(0.0));
		primaryAccount.setAccountNumber(accountGen());
		
		primaryAccountDao.save(primaryAccount);
		// we haven't generated the id for the primaryAccount object we just created, that will be done
		// automatically when we persist the information in the database, then, we need to use de DAO
		// to retrieve the object with the full data filled
		return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());
	}
	
	public SavingsAccount createSavingsAccount() {
		SavingsAccount savingsAccount = new SavingsAccount();
		savingsAccount.setAccountBalance(new BigDecimal(0.0));
		savingsAccount.setAccountNumber(accountGen());
		
		savingsAccountDao.save(savingsAccount);
		
		return savingsAccountDao.findByAccountNumber(savingsAccount.getAccountNumber());
	}
	
	public void deposit(String accountType, double amount, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		
		if (accountType.equalsIgnoreCase("Primary")) {
			PrimaryAccount primaryAccount = user.getPrimaryAccount();
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			
			Date date = new Date();
			PrimaryTransaction primaryTransaction = new PrimaryTransaction(
					date, 
					"Deposit to primary account", 
					"Account", "Finished", 
					amount, 
					primaryAccount.getAccountBalance(), primaryAccount);
			transactionService.savePrimaryDepositTransaction(primaryTransaction);
			
		} else if (accountType.equalsIgnoreCase("Savings")) {
			SavingsAccount savingsAccount = user.getSavingsAccount();
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
			savingsAccountDao.save(savingsAccount);
			
			Date date = new Date();
			SavingsTransaction savingsTransaction = new SavingsTransaction(
					date, 
					"Deposit to savings account",
					"Account",
					"Finished",
					amount,
					savingsAccount.getAccountBalance(),
					savingsAccount);
			transactionService.saveSavingsDepositTransaction(savingsTransaction);
		}
	}

	@Override
	public void withdraw(String accountType, double amount, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		
		if (accountType.equalsIgnoreCase("Primary")) {
			PrimaryAccount primaryAccount = user.getPrimaryAccount();
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			
			Date date = new Date();
			PrimaryTransaction primaryTransaction = new PrimaryTransaction(
					date, 
					"Withdraw from primary account", 
					"Account", "Finished", 
					amount, 
					primaryAccount.getAccountBalance(), primaryAccount);
			transactionService.savePrimaryWithdrawTransaction(primaryTransaction);
			
		} else if (accountType.equalsIgnoreCase("Savings")) {
			SavingsAccount savingsAccount = user.getSavingsAccount();
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			savingsAccountDao.save(savingsAccount);
			
			Date date = new Date();
			SavingsTransaction savingsTransaction = new SavingsTransaction(
					date, 
					"Withdraw from savings account",
					"Account",
					"Finished",
					amount,
					savingsAccount.getAccountBalance(),
					savingsAccount);
			transactionService.saveSavingsWithdrawTransaction(savingsTransaction);
		}
		
	}

}
