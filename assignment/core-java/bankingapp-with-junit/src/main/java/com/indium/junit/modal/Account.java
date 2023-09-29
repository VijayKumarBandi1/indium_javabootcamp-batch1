package com.indium.junit.modal;



public class Account {

	private int accountNumber;
	private String accountHolderName;
	private double balance;
	private AccountType accType;
	
	
	
	
	public Account(int accountNumber, String accountHolderName, double balance, AccountType accType) {
		super();
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
		this.accType = accType;
	}
	
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}


	public AccountType getAccType() {
		return accType;
	}


	public void setAccType(AccountType accType) {
		this.accType = accType;
	}
	
	

	

}
