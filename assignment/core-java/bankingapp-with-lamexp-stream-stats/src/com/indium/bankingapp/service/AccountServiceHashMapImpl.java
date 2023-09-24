package com.indium.bankingapp.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.indium.bankingapp.enums.AccountType;
import com.indium.bankingapp.model.Account;

public class AccountServiceHashMapImpl implements AccountService {

	static AccountService accountService = new AccountServiceHashMapImpl();

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

   //******************************print statistics start***************************************
    
  //*************************** The number of accounts is more than 1 lakh ************************* 
    @Override
    public void getCountOfAccountsAboveBalance(double balance) {
		long count = accounts.values().stream()
				.filter(account -> account.getBalance() > balance)
				.count();
		  System.out.println("Number of accounts with balance above " + balance + ": " + count);
		      
	}
    
    //************************* Number of accounts by account type****************************
    @Override
    public void getNoOfAccountsByAccountType() {
    	 Map<AccountType, Long> accountTypeCounts = accounts.values()
                 .stream()
                 .collect(Collectors.groupingBy(Account::getAccType, Collectors.counting()));
         System.out.println(" Show no of account by account type:");
         accountTypeCounts.forEach((accountType, count) -> System.out.println(accountType + ": " + count));
	}
    
    //*******************Number of accounts by account type with sorting*********************
    @Override
    public void getNoOfAccountsByAccountTypeWithSorted() {
        Map<AccountType, Long> accountTypeCounts = accounts.values()
                .stream()
                .collect(Collectors.groupingBy(Account::getAccType, Collectors.counting()));

        System.out.println("Number of accounts by account type with sorting");
        accountTypeCounts.entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> entry.getKey().toString().toLowerCase()))
                .forEach(entry -> System.out.println("Account Type: " + entry.getKey() + ", Count: " + entry.getValue()));
    }

    
 //******************** Average Balance By AccountType***********************************  
    public void getAverageBalanceByAccountType() {
    	 Map<AccountType, Double> avgBalanceByAccountType = accounts.values()
                 .stream()
                 .collect(Collectors.groupingBy(Account::getAccType, Collectors.averagingDouble(Account::getBalance)));
                 System.out.println("Average balance by account type:");
         avgBalanceByAccountType.forEach((accountType, avgBalance) -> System.out.println("Account Type: " + accountType + ", Avg Balance: " + avgBalance));
	}
    
 //********************* Account Id's ByAccount Name ************************************ 
    @Override
    public void getAccountIdsByAccountName(String accountName) {
        if (accountName == null || accountName.trim().isEmpty()) {
            System.out.println("Account name is empty or null. Please provide a valid account name.");
            return; 
        }

        Collection<Account> allAccounts = getAllAccounts();
        if (allAccounts.size()==0) {
            System.out.println("No accounts found.");
            return; 
        }

        System.out.println("Account IDs with account name containing \"" + accountName + "\":");
        allAccounts.stream()
                .filter(account -> {
                    String accountHolderName = account.getAccountHolderName();
                    return accountHolderName != null && accountHolderName.toLowerCase().contains(accountName.toLowerCase());
                })
                .map(Account::getAccountNumber)
                .forEach(System.out::println);
    }

  //******************************print statistics end***************************************    

	
//****************************Import*******************************************	
	
	 public void importData(){
	        Runnable obj1 = new Runnable() {
	            @Override
	            public void run() {
	                int counter =0;
	                try(BufferedReader reader = new BufferedReader(new FileReader("./input/input.txt"))){
	                    String line;
	                    while((line = reader.readLine())!= null){
	                        String[] parts = line.split(",");
	                        String accHolderName = parts[0];
	                        double balance = Double.parseDouble(parts[1]);
	                        AccountType accType = AccountType.valueOf(parts[3].toUpperCase());
	                        int accNumber = Integer.parseInt(parts[2]);
	                        Account newAccount = new Account(accNumber,accHolderName,balance,accType);
	                        accounts.put(accNumber,newAccount);
	                        counter++;
	                    }
	                    System.out.println("imported "+counter+" records");
	                }catch(Exception e){
	                    System.out.println(e.getMessage());
	                }

	            }
	        };
	        Thread t1 = new Thread(obj1);
	        t1.setName("Import thread");
	        t1.start();
	        System.out.println("importing data using "+t1.getName());
	        try {
	            Thread.sleep(100);
	        } catch (InterruptedException e) {
	            throw new RuntimeException(e);
	        }
	    }

	//*****************************export*********************************************
	 public void exportData(){
	        Runnable obj1 = new Runnable() {
	            @Override
	            public void run() {
	                int counter = 0;
	                try(PrintWriter out = new PrintWriter(new FileWriter("./output/output.txt"))){
	                    for(Account account : accounts.values() ){
	                        StringBuilder accountRecord = new StringBuilder();
	                        accountRecord.append(account.getAccountHolderName())
	                                .append(",")
	                                .append(account.getBalance())
	                                .append(",")
	                                .append(account.getAccountNumber())
	                                .append(",")
	                                .append(account.getAccType())
	                                .append("\n");
	                        out.write(accountRecord.toString());
	                        counter++;
	                    }
	                    System.out.println("exported "+counter+" account details");
	                }catch(Exception e){
	                    System.out.println(e.getMessage());
	                }
	            }
	        };
	        Thread t2 = new Thread(obj1);
	        t2.setName("Export thread");
	        t2.start();
	        System.out.println("Exporting data using "+t2.getName());
	        try {
	            Thread.sleep(100);
	        } catch (InterruptedException e) {
	            throw new RuntimeException(e);
	        }
	    }
//****************************delete Account*************************************************
    @Override
    public boolean deleteAccount(Account account) {
        return accounts.values().remove(account);
    }
    
 

}
