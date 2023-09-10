package com.indium.bankingapp.service;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import com.indium.bankingapp.model.Account;

public class AccountServiceTreeMapImpl implements AccountService {

    private TreeMap<Integer, Account> accounts = new TreeMap<>();

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

    //****************************delete Account*************************************************
    @Override
    public boolean deleteAccount(Account account) {
        return accounts.values().remove(account);
    }
}

