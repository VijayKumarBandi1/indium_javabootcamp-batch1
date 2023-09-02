package com.indium.bankingapp.service;

import com.indium.bankingapp.model.Account;

public class AccountServiceArrImpl implements AccountService {

    private Account[] accounts = new Account[10];
    private int accountCount = 0;

    //*********************************create Account*************************************************
    @Override
    public boolean createAccount(Account account) {
        if (accountCount < accounts.length) {
            accounts[accountCount] = account;
            accountCount++;
            return true;
        }
        return false; // Array is full, cannot create more accounts
    }
    
    //*************************view All Accounts***********************************************
    @Override
    public Account[] getAllAccounts() {
        Account[] allAccounts = new Account[accountCount];
        System.arraycopy(accounts, 0, allAccounts, 0, accountCount);
        return allAccounts;
    }

    //****************************view Account****************************************************
    @Override
    public Account getAccount(int id) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] != null && accounts[i].getAccountNumber() == id) {
                return accounts[i];
            }
        }
        return null; // Account not found
    }
    
    //*******************************Update Account************************************************
    @Override
    public boolean updateAccount(int id, Account updatedAccount) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] != null && accounts[i].getAccountNumber() == id) {
                accounts[i] = updatedAccount;
                return true;
            }
        }
        return false; // Account not found
    }

    //****************************Deposit Amount**********************************************
    @Override
	public boolean deposit(int id, double amount) {
    	for (int i = 0; i < accountCount; i++) {
            if (accounts[i] != null && accounts[i].getAccountNumber() == id) {
            	double updateAmount=accounts[i].getBalance()+amount;
                accounts[i].setBalance(updateAmount);
                System.out.println("Deposit Amount: "+amount);
                System.out.println("Current Balance: "+accounts[i].getBalance());
                return true;
            }
        }
		return false;
	}
    
    //***************************withdraw Amount*********************************************
    @Override
	public boolean withdraw(int id, double amount) {
    	for (int i = 0; i < accountCount; i++) {
            if (accounts[i] != null && accounts[i].getAccountNumber() == id) {
            	double checkBlance=accounts[i].getBalance();
            	if(checkBlance<amount) {
            		System.out.println("Insufficient funds.");
            		return false;
            	}
            	double updateAmount=accounts[i].getBalance()-amount;
                accounts[i].setBalance(updateAmount);
                System.out.println("Withdrawal Amount: "+amount);
                System.out.println("Current Balance: "+accounts[i].getBalance());
                return true;
            }
        }
		return false;
	}
    
    //****************************delete Account*************************************************
    @Override
    public boolean deleleAccount(Account account) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] != null && accounts[i].getAccountNumber() == account.getAccountNumber()) {
                // Shift accounts to remove the deleted one
                for (int j = i; j < accountCount - 1; j++) {
                    accounts[j] = accounts[j + 1];
                }
                accounts[accountCount - 1] = null; // Clear the last element
                accountCount--;
                return true;
            }
        }
        return false; // Account not found
    }

	
    
}
