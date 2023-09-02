package com.indium.bankingapp.service;

import com.indium.bankingapp.model.Account;

public interface AccountService {

   	 public boolean createAccount(Account account);
	    public boolean updateAccount(int id, Account account);
	    public boolean deleleAccount(Account account);
	    public Account getAccount(int id);
	    public Account[] getAllAccounts();
	    public boolean deposit(int id, double amount);
	    public boolean withdraw(int id, double amount);
	
}
