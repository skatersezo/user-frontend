package com.iespinosa.userFront.service;

import java.security.Principal;

import com.iespinosa.userFront.domain.PrimaryAccount;
import com.iespinosa.userFront.domain.SavingsAccount;

public interface AccountService {

	PrimaryAccount createPrimaryAccount();
	SavingsAccount createSavingsAccount();
	void deposit(String accountType, double amount, Principal principal);
	void withdraw(String accountType, double amount, Principal principal);


}
