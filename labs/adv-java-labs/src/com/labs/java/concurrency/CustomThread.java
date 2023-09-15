package com.labs.java.concurrency;

import java.util.concurrent.ThreadFactory;

public class CustomThread extends Thread{

	 @Override
	    public void run() {
	        System.out.println("Entered into " + Thread.currentThread().getName());
	        System.out.println("********************");
	        System.out.println("Exited from " + Thread.currentThread().getName());
	    }

}
