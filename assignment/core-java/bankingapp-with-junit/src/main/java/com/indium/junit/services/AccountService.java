package com.indium.junit.services;


import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.indium.junit.modal.Account;



public interface AccountService {

   	 public boolean createAccount(Account account);
	    public boolean updateAccount(int id, Account account);
	    public boolean deleteAccount(Account account);
	    public Account getAccount(int id);
	    public Collection<Account> getAllAccounts();
	    public boolean deposit(int id, double amount);
	    public boolean withdraw(int id, double amount);
	    public void importData();
	    public void exportData();
	    void getCountOfAccountsAboveBalance(double balance);
		void getNoOfAccountsByAccountType();
		void getNoOfAccountsByAccountTypeWithSorted();
		void getAverageBalanceByAccountType();
		void getAccountIdsByAccountName(String accountName) ;
	
}
