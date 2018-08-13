package com.iespinosa.userFront.service.UserServiceImpl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import com.iespinosa.userFront.dao.PrimaryAccountDao;
import com.iespinosa.userFront.dao.SavingsAccountDao;
import com.iespinosa.userFront.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iespinosa.userFront.dao.PrimaryTransactionDao;
import com.iespinosa.userFront.dao.SavingsTransactionDao;
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

	@Autowired
	private PrimaryAccountDao primaryAccountDao;

	@Autowired
	private SavingsAccountDao savingsAccountDao;
	 
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

	@Override
	public void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception {
		if (transferFrom.equalsIgnoreCase("Primary") && transferTo.equalsIgnoreCase("Savings")) {
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			savingsAccountDao.save(savingsAccount);

			Date date = new Date();

			PrimaryTransaction primaryTransaction = new PrimaryTransaction(
					date,
					"Between account transfer from " + transferFrom + " to " + transferTo,
					"Account",
					"Finished",
					Double.parseDouble(amount),
					primaryAccount.getAccountBalance(),
					primaryAccount);
			primaryTransactionDao.save(primaryTransaction);
		} else if (transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Primary")) {
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			savingsAccountDao.save(savingsAccount);

			Date date = new Date();

			SavingsTransaction savingsTransaction = new SavingsTransaction(
					date,
					"Between account transfer from " + transferFrom + " to " + transferTo,
					"Account",
					"Finished",
					Double.parseDouble(amount),
					savingsAccount.getAccountBalance(),
					savingsAccount);
			savingsTransactionDao.save(savingsTransaction);
		} else {
			throw new Exception("Invalid transfer");
		}
	}

	@Override
	public List<Recipient> findRecipientList(Principal principal) {
		String username = principal.getName();
		List<Recipient> recipientList = recipientDao.findAll().stream()
				.filter(recipient -> username.equals(recipient.getUser().getUsername()))
				.collect(collectors.toList());

		return recipientList;
	}

	@Override
	public void saveRecipient(Recipient recipient) {
		return recipientDao.save(recipient);

	}

	@Override
	public Recipient findRecipientByName(String recipientName) {
		return recipientDao.findByName(recipientName);
	}

	@Override
	public void deleteRecipientByName(String recipientName) {
		recipientDao.deleteByName(recipientName);
	}


}
