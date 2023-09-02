package com.labs.java.oops;

public class DepositAccount extends Account implements PreCloseabel,AutoRenewable{
	
	private double prencipal;
	private int tenure;
	
	

	public DepositAccount() {
		
	}

	public DepositAccount(int id, String name, boolean active, double roi,double prencipal,int tenure) {
		super(id, name, active, roi);
		this.prencipal=prencipal;
		this.tenure=tenure;
		
	}

	@Override
 protected	void openAccount() {
		System.out.println("deposite open account");
		
	}
	
	@Override
 protected 	void closeAccount() {
		System.out.println("deposite  close account ");
	}

	public void preClosure() {
		System.out.println("deposite preClosure");
	}
	
	public double getPrencipal() {
		return prencipal;
	}

	public void setPrencipal(double amount) {
		this.prencipal = amount;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}



	@Override
	public void autoRenew() {
		System.out.println("deposite auto renew");
		
	}
	
	protected double getMaturityAmount() {
		return this.prencipal*tenure*(7.1/100);
	}
	
	

}
