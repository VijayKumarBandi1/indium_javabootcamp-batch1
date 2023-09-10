package com.indium.bankingapp.service;


import java.util.Collection;
import java.util.List;

import com.indium.bankingapp.model.Account;

public interface AccountService {

   	 public boolean createAccount(Account account);
	    public boolean updateAccount(int id, Account account);
	    public boolean deleteAccount(Account account);
	    public Account getAccount(int id);
	    public Collection<Account> getAllAccounts();
	    public boolean deposit(int id, double amount);
	    public boolean withdraw(int id, double amount);
	
}
