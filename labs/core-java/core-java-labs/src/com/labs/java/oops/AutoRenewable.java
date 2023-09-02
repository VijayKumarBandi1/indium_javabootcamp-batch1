package com.labs.java.oops;

public interface AutoRenewable {
	
	int renewalDuration=3;
	// public void autoRenew();
	 
	 //java 8 
	 public default void autoRenew() {
		 print();
	 }
	 
	 //java 9
	 private void print() {
		System.out.println("Default Auot Renewal");
	}

}
