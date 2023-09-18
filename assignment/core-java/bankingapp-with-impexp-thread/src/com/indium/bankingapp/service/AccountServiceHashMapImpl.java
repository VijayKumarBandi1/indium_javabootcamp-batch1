package com.indium.bankingapp.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
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
	
//****************************Import*******************************************	
//	public void importData(){
//        int counter =0;
//        try(BufferedReader reader = new BufferedReader(new FileReader("./input/input.txt"))){
//            String line;
//            while((line = reader.readLine())!= null){
//                String[] parts = line.split(",");
//                String accHolderName = parts[0];
//                double balance = Double.parseDouble(parts[1]);
//                AccountType accType = AccountType.valueOf(parts[3].toUpperCase());
//                int accNumber = Integer.parseInt(parts[2]);
//                Account newAccount = new Account(accNumber,accHolderName,balance,accType);
//                accounts.put(accNumber,newAccount);
//                counter++;
//            }
//            System.out.println("imported "+counter+" records");
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
	
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
//    public void exportData(){
//        int counter = 0;
//        try(PrintWriter out = new PrintWriter(new FileWriter("./output/output.txt"))){
//            for(Account account : accounts.values() ){
//                StringBuilder accountRecord = new StringBuilder();
//                accountRecord.append(account.getAccountHolderName())
//                        .append(",")
//                        .append(account.getBalance())
//                        .append(",")
//                        .append(account.getAccountNumber())
//                        .append(",")
//                        .append(account.getAccType())
//                        .append("\n");
//                out.write(accountRecord.toString());
//                counter++;
//            }
//            System.out.println("exported "+counter+" account details");
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
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
