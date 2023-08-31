package com.labs.java.oops;



public abstract class Account {
	
	

	int id;
	String name =new String();
	
	boolean active;
	
	{
		System.out.println("instance block ");
	}
	
	Account(){
		System.out.println("Account default cons block");
		System.out.println(this.name instanceof String);
	}
	
	Account(int id,String name,boolean active){
	     this.id=id;
	     this.name=name;
	     this.active=active;
		System.out.println("Account overload cons block");	
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

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", active=" + active
				+ "]";
	}
	
	

}
