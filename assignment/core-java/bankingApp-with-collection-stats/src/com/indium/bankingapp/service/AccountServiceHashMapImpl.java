package com.indium.bankingapp.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.indium.bankingapp.enums.AccountType;
import com.indium.bankingapp.model.Account;

public class AccountServiceHashMapImpl implements AccountService {

    private HashMap<Integer, Account> accounts = new HashMap<>();

    //*********************************create Account*************************************************
    @Override
    public boolean createAccount(Account account) {
        if (!accounts.containsKey(account.getAccountNumber())) {
            accounts.put(account.getAccountNumber(), account);
            return true;
        }
        return false; 
    }

    //*************************view All Accounts***********************************************
    @Override
    public Collection<Account> getAllAccounts() {
        return accounts.values();
    }

    //****************************view Account****************************************************
    @Override
    public Account getAccount(int id) {
        return accounts.get(id);
    }

    //*******************************Update Account************************************************
    @Override
    public boolean updateAccount(int id, Account updatedAccount) {
        if (accounts.containsKey(id)) {
            accounts.put(id, updatedAccount);
            return true;
        }
        return false; 
    }

    //****************************Deposit Amount**********************************************
    @Override
    public boolean deposit(int id, double amount) {
        Account account = accounts.get(id);
        if (account != null) {
            double balance = account.getBalance();
            double updatedBalance = balance + amount;
            System.out.println("Previous Balance: " + balance);
            System.out.println("Deposit Amount: " + amount);
            System.out.println("Current Balance: " + updatedBalance);
            account.setBalance(updatedBalance);
            return true;
        }
        return false; 
    }

    //***************************withdraw Amount*********************************************
    @Override
    public boolean withdraw(int id, double amount) {
        Account account = accounts.get(id);
        if (account != null) {
            double checkBalance = account.getBalance();
            if (checkBalance < amount) {
                System.out.println("Insufficient funds.");
                return false;
            }
            double updatedAmount = checkBalance - amount;
            account.setBalance(updatedAmount);
            System.out.println("Withdrawal Amount: " + amount);
            System.out.println("Current Balance: " + updatedAmount);
            return true;
        }
        return false; 
    }

   //******************************print statistics***************************************
	@Override
	public void printStatistics() {
		
		 if (accounts.isEmpty()) {
	            System.out.println("No accounts found.");
	            return;
	        }

	        double totalBalance = 0;
	        int countAccountsAbove1Lac = 0;
	        Map<AccountType, Integer> accountTypeCount = new HashMap<>();
	        Map<AccountType, Double> accountTypeTotalBalance = new HashMap<>();
	        for (Account account : accounts.values()) {
	            totalBalance += account.getBalance();

	            if (account.getBalance() > 100000) {
	                countAccountsAbove1Lac++;
	            }

	            AccountType accountType = account.getAccType();
	            accountTypeCount.put(accountType, accountTypeCount.getOrDefault(accountType, 0) + 1);
	            accountTypeTotalBalance.put(accountType, accountTypeTotalBalance.getOrDefault(accountType, 0.0) + account.getBalance());

	            }
	        System.out.println("\na] No of accounts which has balance more than 1 lac: " + countAccountsAbove1Lac);

	        System.out.println("\nb] Show no of account by account type:");
	        for (Map.Entry<AccountType, Integer> entry : accountTypeCount.entrySet()) {
	            System.out.println(entry.getKey() + ": " + entry.getValue());
	        }

	        System.out.println("\nc] Show no of accounts by account type with sorting:");
	        accountTypeCount.entrySet().stream()
	                .sorted(Map.Entry.comparingByValue())
	                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

	        System.out.println("\nd] Show avg balance by account type:");
	        for (Map.Entry<AccountType, Double> entry : accountTypeTotalBalance.entrySet()) {
	            AccountType accountType = entry.getKey();
	            double totalBalanceByType = entry.getValue();
	            int countByType = accountTypeCount.get(accountType);
	            double avgBalance = totalBalanceByType / countByType;
	            System.out.println(accountType + ": " + avgBalance);
	        }
	    }
		
//****************************delete Account*************************************************
    @Override
    public boolean deleteAccount(Account account) {
        return accounts.values().remove(account);
    }
	
}
