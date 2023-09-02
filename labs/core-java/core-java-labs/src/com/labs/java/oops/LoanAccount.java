package com.labs.java.oops;

public class LoanAccount extends Account implements PreCloseabel{

	protected double loanAmount;
	
	@Override
	void openAccount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preClosure() {
		// TODO Auto-generated method stub
		
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	
	

}
