package com.indium.bankingapp.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.indium.bankingapp.model.Account;

public class AccountServiceHashSetImpl implements AccountService {

    private HashSet<Account> accounts = new HashSet<>();

    //*********************************create Account*************************************************
    @Override
    public boolean createAccount(Account account) {
        return accounts.add(account);
    }

    //*************************view All Accounts***********************************************
    @Override
    public Collection<Account> getAllAccounts() {
        if (accounts.isEmpty())
            return null;
        return accounts;
    }

    //****************************view Account****************************************************
    @Override
    public Account getAccount(int id) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == id)
                return account;
        }
        return null;
    }

    //*******************************Update Account************************************************
    @Override
    public boolean updateAccount(int id, Account updatedAccount) {
        Iterator<Account> iterator = accounts.iterator();
        while (iterator.hasNext()) {
            Account account = iterator.next();
            if (account.getAccountNumber() == id) {
                iterator.remove();;
                return accounts.add(updatedAccount);
            }
        }
        return false; // Account not found
    }

    //****************************Deposit Amount**********************************************
    @Override
    public boolean deposit(int id, double amount) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == id) {
                double balance = account.getBalance();
                double updatedBalance = balance + amount;
                System.out.println("Previous Balance: " + balance);
                System.out.println("Deposit Amount: " + amount);
                System.out.println("Current Balance: " + updatedBalance);
                account.setBalance(updatedBalance);
                return true;
            }
        }
        return false;
    }

    //***************************withdraw Amount*********************************************
    @Override
    public boolean withdraw(int id, double amount) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == id) {
                double checkBalance = account.getBalance();
                if (checkBalance < amount) {
                    System.out.println("Insufficient funds.");
                    return false;
                }
                double updatedAmount = account.getBalance() - amount;
                account.setBalance(updatedAmount);
                System.out.println("Withdrawal Amount: " + amount);
                System.out.println("Current Balance: " + account.getBalance());
                return true;
            }
        }
        return false;
    }

    //****************************delete Account*************************************************
    @Override
    public boolean deleteAccount(Account account) {
        return accounts.remove(account);
    }
}
