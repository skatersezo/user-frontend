package com.iespinosa.userFront.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PrimaryAccount {

	// Primary Account fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int accountNumber;
	// using big decimal we avoid double type issues on calcs
	// System.out.println(1.03 - .42);
	// prints out 0.6100000000001
	// for operations we use the own methods:
	// accountBalance.add(5); instead of: accountBalance + 5;
	private BigDecimal accountBalance; 
	// Primary Account relationships
	// JsonIgnore will break the infinite loop that could cause to be refering this field
	@OneToMany(mappedBy = "primaryAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<PrimaryTransaction> primaryTransaction;
	
	// Getters and Setters
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	public List<PrimaryTransaction> getPrimaryTransactionList() {
		return primaryTransaction;
	}
	public void setPrimaryTransaction(List<PrimaryTransaction> primaryTransaction) {
		this.primaryTransaction = primaryTransaction;
	}
	
	// Getters and setters
	
	
}
