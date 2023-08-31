package com.labs.java.oops;

public class SavingsAccount extends Account{
	
 private double balance;
 protected	double roi;

	public SavingsAccount() {
		System.out.println("saving default cons");
	}
	
	public SavingsAccount(int id, String name, boolean active, double balance, double roi) {
		super(id, name ,active);
		System.out.println("saving overload cons");
	}
	
	@Override
	void openAccount() {
		System.out.println("saving open");
	}

	@Override
	void closeAccount() {
		System.out.println("saving close");
	}
	
	void deposit(double amount) {
		this.balance+=amount;
	}
	
	void  withdraw (double amount ) {
		if(balance>amount) {
			System.out.println("insufficient balance ");
			return;
		}
		this.balance-=amount;
	}
	
	@Override
	public String toString() {
		return super.toString()+ " balance="+balance+" roi="+roi;
	}
	
}
