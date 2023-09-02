package com.labs.java.oops;



public abstract class Account {
	
	

 private int id;
 private String name ;
 private boolean active;
 private double roi;
	
	
	{
		System.out.println("instance block ");
	}
	
 
	public Account() {
		
	}
	
	public Account(int id, String name, boolean active, double roi) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
		this.roi = roi;
	}

	abstract void openAccount() ;
	
	void closeAccount() {
		System.out.println("Account Closed");
	}
	
//	void deposit(double amount) {
//		this.balance+=amount;
//	}
//	
//	void withdrawal(double amount) {
//		this.balance-=amount;
//	}
//	
//	double checkBalance() {
//		return this.balance;
//	}

	
	
	

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", active=" + active + ", roi=" + roi + "]";
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getRoi() {
		return roi;
	}

	public void setRoi(double roi) {
		this.roi = roi;
	}
	
	
	
	

}
