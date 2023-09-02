package com.indium.bankingapp;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.indium.bankingapp.model.Account;
import com.indium.bankingapp.service.AccountService;
import com.indium.bankingapp.service.AccountServiceArrImpl;

public class BankingAppMain {

	static Scanner scanner = new Scanner(System.in);
	static AccountService accountService = new AccountServiceArrImpl();

	public static void main(String[] args) {
		

		int choice;

		do {
			System.out.println("1] Add Account");
			System.out.println("2] View All Accounts");
			System.out.println("3] View Account");
			System.out.println("4] Update Account");
			System.out.println("5] Delete Account");
//			System.out.println("6] Deposit Amount");
//			System.out.println("7] Withdraw Amount");
			System.out.println("6] Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();

			switch (choice) {
// ------------------------------------------------------------Add Account Section-------------------------------------------
			case 1:
				Account newAccount = captureNewAccountDetails();
				boolean created = accountService.createAccount(newAccount);
				if (created) {
					System.out.println("Account added successfully.");
					System.out.println("Account ID: " + newAccount.getAccountNumber());
				} else {
					System.out.println("Cannot create more accounts. The array is full.");
				}
				break;

//---------------------------------------------------------------View All Accounts---------------------------------------------
			case 2:
				Account[] allAccounts = accountService.getAllAccounts();
				if (allAccounts.length == 0) {
					System.out.println("No accounts found.");
				} else {
					System.out.println("All Accounts:");
					System.out.println("+----------------+------------------+----------------+");
					System.out.println("| Account Number | Account Holder   | Account Balance |");
					System.out.println("+----------------+------------------+----------------+");
					for (Account account : allAccounts) {
						if (account != null) {
							System.out.printf("| %-14d | %-16s | $%-14.2f |\n", account.getAccountNumber(),
									account.getAccountHolderName(), account.getBalance());
							System.out.println("+----------------+------------------+----------------+");
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
							existingAccount.getBalance()); // Pass the scanner
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

			// -----------------------------------------delete Account---------------------------------------
			case 5:
				System.out.print("Enter account number to delete: ");
				int deleteAccountNumber = scanner.nextInt();
				Account deleteAccount = accountService.getAccount(deleteAccountNumber);
				if (deleteAccount != null) {
					boolean deleted = accountService.deleleAccount(deleteAccount);
					if (deleted) {
						System.out.println("Account deleted successfully.");
					} else {
						System.out.println("Account deletion failed.");
					}
				} else {
					System.out.println("Account not found.");
				}
				break;
//			case 6:
//				// Implement deposit functionality here
//				break;
//			case 7:
//				// Implement withdrawal functionality here
//				break;

			// ---------------------------------Exiting ---------------------------------------------------
			case 6:
				System.out.println("Exiting the application.");
				break;
			default:
				System.out.println("Invalid choice. Please enter a valid choice.");
			}

			System.out.println();
		} while (choice != 6);

		scanner.close();
	}
//------------------------------------------------------main method end-------------------------------------------

//---------------------------------------------------------------------New Account details----------------------------- 	
	public static Account captureNewAccountDetails() {
	    int accountNumber;
	    String accountHolderName;
	    double initialBalance;

	    while (true) {
	        System.out.print("Enter account number: ");
	        accountNumber = scanner.nextInt();
	        
	        // Check if the account number already exists
	        if (accountService.getAccount(accountNumber) != null) {
	            System.out.println("Account with the same account number already exists. Please choose a different account number.");
	        } else {
	            break; // Valid input, exit the loop
	        }
	    }

	    System.out.print("Enter account holder's name: ");
	    accountHolderName = scanner.next();
	    System.out.print("Enter initial balance: ");
	    initialBalance = scanner.nextDouble();

	    return new Account(accountNumber, accountHolderName, initialBalance);
	}


	// -----------------------------------------------update Account details-----------------------
	public static Account captureUpdateAccountDetails(int accountNumber, double balance) {
	    String accountHolderName;
	    while (true) {
	        // Consume any remaining newline character from the previous input
	        scanner.nextLine();

	        System.out.print("Update account holder's name: ");
	        accountHolderName = scanner.nextLine();

	        // Perform any necessary validation for the account holder's name
	        if (!accountHolderName.isEmpty()) {
	            break; // Valid input, exit the loop
	        } else {
	            System.out.println("Account holder's name cannot be empty. Please enter a valid name.");
	        }
	    }
	    return new Account(accountNumber, accountHolderName, balance);
	}


}
