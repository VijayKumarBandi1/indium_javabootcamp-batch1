package com.indium.bankingapp.service;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

import com.indium.bankingapp.model.Account;

public class AccountServiceArrListImpl implements AccountService {

    private ArrayList<Account> accounts = new ArrayList<>();

    //*********************************create Account*************************************************
    @Override
    public boolean createAccount(Account account) {
        accounts.add(account);
        return true;
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
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == id) {
                accounts.set(i, updatedAccount);
                return true;
            }
        }
        return false; // Account not found
    }

    //****************************Deposit Amount**********************************************
    @Override
    public boolean deposit(int id, double amount) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == id) {
                double balance = accounts.get(i).getBalance();
                double updatedBalance = balance + amount;
                System.out.println("Previous Balance: " + balance);
                System.out.println("Deposit Amount: " + amount);
                System.out.println("Current Balance: " + updatedBalance);
                accounts.get(i).setBalance(updatedBalance);
                return true;
            }
        }
        return false;
    }

    //***************************withdraw Amount*********************************************
    @Override
    public boolean withdraw(int id, double amount) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == id) {
                double checkBalance = accounts.get(i).getBalance();
                if (checkBalance < amount) {
                    System.out.println("Insufficient funds.");
                    return false;
                }
                double updatedAmount = checkBalance - amount;
                accounts.get(i).setBalance(updatedAmount);
                System.out.println("Withdrawal Amount: " + amount);
                System.out.println("Current Balance: " + updatedAmount);
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
