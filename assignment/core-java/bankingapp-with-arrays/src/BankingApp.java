import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount[] accounts = new BankAccount[10]; // Maximum of 10 accounts

        int choice;
        int accountCount = 0;

        do {
            System.out.println("1] Add Account");
            System.out.println("2] View All Accounts");
            System.out.println("3] View Account");
            System.out.println("4] Update Account");
            System.out.println("5] Delete Account");
            System.out.println("6] Deposit Amount");
            System.out.println("7] Withdraw Amount");
            System.out.println("8] Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
            // ---------------------------------------------Add account action--------------------------------- --------------------
                case 1:
                    if (accountCount < accounts.length) {
                        System.out.print("Enter account number: ");
                        int accountNumber = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter account holder name: ");
                        String accountHolderName = scanner.nextLine();
                        System.out.print("Enter initial balance: ");
                        double initialBalance = scanner.nextDouble();

                        accounts[accountCount] = new BankAccount(accountNumber, accountHolderName, initialBalance);
                        accountCount++;

                        System.out.println("Account added successfully.");
                    } else {
                        System.out.println("Maximum account limit reached.");
                    }
                    break;
                    
                 //---------------------------------------------- view all accounts--------------------------------------------------
                case 2:
                	 System.out.println("Viewing all accounts:");
                	    System.out.println("+--------------+-------------------+------------+");
                	    System.out.println("| Account No.  | Account Holder    | Balance    |");
                	    System.out.println("+--------------+-------------------+------------+");
                	    for (int i = 0; i < accountCount; i++) {
                	        String accountNumber = String.format("%-13d", accounts[i].getAccountNumber());
                	        String accountHolder = String.format("%-18s", accounts[i].getAccountHolderName());
                	        String balance = String.format("$%-10.2f", accounts[i].getBalance());
                	        System.out.println("| " + accountNumber + " | " + accountHolder + " | " + balance + " |");
                	        System.out.println();
                	    }
                	    System.out.println("+--------------+-------------------+------------+");
                	    break;
                	    
                //-------------------------------------------------view account-----------------------------------------------
                case 3:
                    System.out.print("Enter account number to view: ");
                    int viewAccountNumber = scanner.nextInt();
                    boolean found = false;
                    for (int i = 0; i < accountCount; i++) {
                        if (accounts[i].getAccountNumber() == viewAccountNumber) {
                            System.out.println("Account Holder: " + accounts[i].getAccountHolderName());
                            System.out.println("Balance: $" + accounts[i].getBalance());
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Account not found.");
                    }
                    break;
                    
                //-------------------------------------------------------update account-----------------------------------------    
                case 4:
                    System.out.print("Enter account number to update: ");
                    int updateAccountNumber = scanner.nextInt();
                    found = false;
                    for (int i = 0; i < accountCount; i++) {
                        if (accounts[i].getAccountNumber() == updateAccountNumber) {
                            System.out.print("Enter new account holder name: ");
                            scanner.nextLine(); // Consume newline
                            String newAccountHolderName = scanner.nextLine();
                            accounts[i] = new BankAccount(accounts[i].getAccountNumber(), newAccountHolderName, accounts[i].getBalance());
                            System.out.println("Account updated successfully.");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Account not found.");
                    }
                    break;
                    
              //-------------------------------------------------------------delete account---------------------------------------      
                case 5:
                    System.out.print("Enter account number to delete: ");
                    int deleteAccountNumber = scanner.nextInt();
                    found = false;
                    for (int i = 0; i < accountCount; i++) {
                        if (accounts[i].getAccountNumber() == deleteAccountNumber) {
                            for (int j = i; j < accountCount - 1; j++) {
                                accounts[j] = accounts[j + 1];
                            }
                            accountCount--;
                            System.out.println("Account deleted successfully.");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Account not found.");
                    }
                    break;
                    
                case 6: //--------------------------------------------------------Deposit--------------------------------------
                    System.out.print("Enter account number for deposit: ");
                    int depositAccountNumber = scanner.nextInt();
                    found = false;
                    for (int i = 0; i < accountCount; i++) {
                        if (accounts[i].getAccountNumber() == depositAccountNumber) {
                            System.out.print("Enter deposit amount: $");
                            double depositAmount = scanner.nextDouble();
                            accounts[i].deposit(depositAmount);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Account not found.");
                    }
                    break;
                    
                case 7: //---------------------------------------------------------Withdraw--------------------------------------
                    System.out.print("Enter account number for withdrawal: ");
                    int withdrawalAccountNumber = scanner.nextInt();
                    found = false;
                    for (int i = 0; i < accountCount; i++) {
                        if (accounts[i].getAccountNumber() == withdrawalAccountNumber) {
                            System.out.print("Enter withdrawal amount: $");
                            double withdrawalAmount = scanner.nextDouble();
                            accounts[i].withdraw(withdrawalAmount);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Account not found.");
                    }
                    break;
                 
                //-----------------------------------------------------------------Exiting-------------------------------------     
                case 8:
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid choice.");
            }

            System.out.println();
        } while (choice != 8);

        scanner.close();
    }
}
