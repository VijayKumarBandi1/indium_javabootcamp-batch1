package com.indium.bankingapp;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.indium.bankingapp.enums.AccountType;
import com.indium.bankingapp.model.Account;
import com.indium.bankingapp.service.*;


public class BankingAppMain {

	static Scanner scanner = new Scanner(System.in);

 static AccountService accountService = new AccountServiceHashMapImpl();

	public static void main(String[] args)  {

		int choice;

		do {
			System.out.println("1] Add Account");
			System.out.println("2] View All Accounts");
			System.out.println("3] View Account");
			System.out.println("4] Update Account");
			System.out.println("5] Deposit Amount");
			System.out.println("6] Withdraw Amount");
			System.out.println("7] Print Statistics");
			System.out.println("8] Import");
            System.out.println("9] Export");
			System.out.println("10] Delete Account");
			System.out.println("11] Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();

			switch (choice) {
// ------------------------------------------------------------Add Account Section-------------------------------------------
			case 1:
				Account newAccount = captureNewAccountDetails();
				boolean created = accountService.createAccount(newAccount);
				if (created) {
					System.out.println("\nAccount added successfully.");
					System.out.println("Account ID: " + newAccount.getAccountNumber());
				} else {
					System.out.println("Cannot create more accounts. The array is full.");
				}
				break;

//---------------------------------------------------------------View All Accounts---------------------------------------------
			case 2:
			     Collection<Account> allAccounts = accountService.getAllAccounts();
				if (allAccounts.size() == 0) {
					System.out.println("No accounts found.");
				} else {
				    System.out.println("All Accounts:");
				    System.out.println("+----------------+----------------+------------------+----------------+");
				    System.out.println("| Account Number | Account Type   | Account Holder   | Account Balance |");
				    System.out.println("+----------------+----------------+------------------+----------------+");
				    for (Account account : allAccounts) {
				        if (account != null) {
				            System.out.printf("| %-14d | %-14s | %-16s | $%-14.2f |\n",
				                    account.getAccountNumber(),
				                    account.getAccType(),
				                    account.getAccountHolderName(),
				                    account.getBalance());
				            System.out.println("+----------------+----------------+------------------+----------------+");
				        }
				    }
				}

				break;

//---------------------------------------------------------------View Account By id--------------------------------------
			case 3:
				System.out.print("Enter account number to view: ");
				int viewAccountNumber = scanner.nextInt();
				Account viewAccount = accountService.getAccount(viewAccountNumber);
				if (viewAccount != null) {
					System.out.println("\nAccount Number: " + viewAccount.getAccountNumber());
					System.out.println("Account Holder: " + viewAccount.getAccountHolderName());
					System.out.println("Account Balance: $" + viewAccount.getBalance());
				} else {
					System.out.println("Account not found.");
				}
				break;

//--------------------------------------------------------------------Update Account-------------------------------------
			case 4:
				System.out.print("Enter account number to update: ");
				int updateAccountNumber = scanner.nextInt();
				Account existingAccount = accountService.getAccount(updateAccountNumber);
				if (existingAccount != null) {
					Account updatedAccount = captureUpdateAccountDetails(updateAccountNumber,
							existingAccount.getBalance(),existingAccount.getAccType()); // Pass the scanner
					boolean updated = accountService.updateAccount(updateAccountNumber, updatedAccount);
					if (updated) {
						System.out.println("Account updated successfully.");
					} else {
						System.out.println("Account update failed.");
					}
				} else {
					System.out.println("Account not found.");
				}
				break;

//---------------------------------------Deposit Amount-----------------------------------------
			case 5:
				System.out.print("Enter account number to deposit: ");
				int depositAccountNumber = scanner.nextInt();
				Account depositAccount = accountService.getAccount(depositAccountNumber);

				if (depositAccount != null) {
					System.out.println("Enter Amount");
					double amount = scanner.nextInt();
					boolean deposit = accountService.deposit(depositAccountNumber, amount);
					if (deposit) {
						System.out.println("Amount successfully deposited");
					} else {
						System.out.println("Deposit failed");
					}

				} else {
					System.out.println("Account not found.");
				}

				break;

// --------------------------------------withdraw Amount-----------------------------------
			case 6:
				System.out.print("Enter account number to withdraw: ");
				int withdrawAccountNumber = scanner.nextInt();
				Account withdrawAccount = accountService.getAccount(withdrawAccountNumber);

				if (withdrawAccount != null) {
					System.out.println("Enter Amount");
					double amount = scanner.nextInt();
					boolean withdraw = accountService.withdraw(withdrawAccountNumber, amount);
					if (withdraw) {
						System.out.println("Successful withdrawal of money");
					} else {
						System.out.println("withdrawal failed");
					}

				} else {
					System.out.println("Account not found.");
				}

				break;
				
//------------------------------------ print statistics------------------------------------		
			case 7:
				printStatistics();
				break;
				
//---------------------------------Import---------------------------------------				
			case 8:
				accountService.importData();
				break;
				
//------------------------------------Export---------------------------------------				
			case 9:
				accountService.exportData();
				break;

// -----------------------------------------delete Account---------------------------------------
			case 10:
				System.out.print("Enter account number to delete: ");
				int deleteAccountNumber = scanner.nextInt();
				Account deleteAccount = accountService.getAccount(deleteAccountNumber);
				if (deleteAccount != null) {
					boolean deleted = accountService.deleteAccount(deleteAccount);
					
					if (deleted) {
						System.out.println("Account deleted successfully.");
					} else {
						System.out.println("Account deletion failed.");
					}
				} else {
					System.out.println("Account not found.");
				}
				break;

//---------------------------------Exiting ---------------------------------------------------
			case 11:
				System.out.println("Exiting the application.");
				break;
			default:
				System.out.println("Invalid choice. Please enter a valid choice.");
			}

			System.out.println();
		} while (choice != 11);

		scanner.close();
	}
//------------------------------------------------------main method end-------------------------------------------

//---------------------------------------------------------------------New Account details----------------------------- 	
	public static Account captureNewAccountDetails() {
		int accountNumber;
		String accountHolderName;
		double initialBalance;
		AccountType myAccount=AccountType.SAVING;
		byte selectAccountType;

		while (true) {
			
			
		do {
			System.out.println("Select account type");
			System.out.println("1] SAVING");
			System.out.println("2] CURRENT");
			 selectAccountType = scanner.nextByte();
			switch (selectAccountType) {
			case 1: 
				myAccount = AccountType.SAVING;
				break;
				
			case 2: 
				myAccount = AccountType.CURRENT;
				break;
				
			default:
				System.out.println("Invalid choice. Please enter a valid choice.");
			}
			
			
		}while(selectAccountType>2||selectAccountType<1);
			System.out.print("Enter account number: ");
			accountNumber = scanner.nextInt();

			// Check if the account number already exists
			if (accountService.getAccount(accountNumber) != null) {
				System.out.println("Account number already exists. Please choose a different account number.");
			} else {
				break;
			}
		}

		scanner.nextLine();
		System.out.print("Enter account holder's name: ");
		accountHolderName = scanner.nextLine();
		System.out.print("Enter initial balance: ");
		initialBalance = scanner.nextDouble();

		return new Account(accountNumber, accountHolderName, initialBalance, myAccount);
	}

//-----------------------------------------------update Account details-----------------------
	public static Account captureUpdateAccountDetails(int accountNumber, double balance, AccountType accType) {
		String accountHolderName;

		// Consume the newline character left in the input buffer
		scanner.nextLine();

		while (true) {
			System.out.print("Update account holder's name: ");
			accountHolderName = scanner.nextLine();

			
			if (!accountHolderName.isEmpty()) {
				break;
			} else {
				System.out.println("Account holder's name cannot be empty. Please enter a valid name.");
			}
		}

		return new Account(accountNumber,accountHolderName,balance,accType);
	}
	
	//--------------------------------------print statistics method----------------------
	
	private static void printStatistics() {

		System.out.println("Banking App Statistics: ");
		System.out.println("1. No of accounts which has balance more than 1 lac");
		System.out.println("2. Show no of account by account type");
		System.out.println("3. Show no of accounts by account type with sorting");
		System.out.println("4. Show avg balance by account type");
		System.out.println("5. List account ids whose account name contains given name");

		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		scanner.nextLine();

		switch (choice){
			case 1:
				accountService.getCountOfAccountsAboveBalance(100000);
				break;
			case 2:
				accountService.getNoOfAccountsByAccountType();
				break;
			case 3:
				accountService.getNoOfAccountsByAccountTypeWithSorted();
				break;
			case 4:
				accountService.getAverageBalanceByAccountType();
				break;
			case 5:
				System.out.println("Enter account name to search: ");
				String accountName = scanner.nextLine();
				accountService.getAccountIdsByAccountName(accountName);
				break;
			default:
				System.err.println("Invalid choice. ");
		}
	}

}
