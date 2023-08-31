package com.labs.java.oops;

import java.lang.reflect.Constructor;
import java.time.temporal.TemporalAmount;

public class Account {
	
	int id;
	String name =new String();
	String type;
	
	double balance;
	boolean active;
	
	{
		System.out.println("instance block ");
	}
	
	Account(){
		System.out.println("default cons block");
		System.out.println(this.name instanceof String);
	}
	
	Account(int id,String name,String type,double balance,boolean active){
	     this.id=id;
	     this.name=name;
	     this.type=type;
	     this.balance=balance;
	     this.active=active;
		System.out.println("overload cons block");	
	}
	
	void openAccount() {
		System.out.println("Account Opened");
	}
	
	void closeAccount() {
		System.out.println("Account Closed");
	}
	
	void deposit(double amount) {
		this.balance+=amount;
	}
	
	void withdrawal(double amount) {
		this.balance-=amount;
	}
	
	double checkBalance() {
		return this.balance;
	}

}
