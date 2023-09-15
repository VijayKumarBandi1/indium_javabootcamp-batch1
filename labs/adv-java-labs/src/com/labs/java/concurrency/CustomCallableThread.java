package com.labs.java.concurrency;

import java.util.concurrent.Callable;

public class CustomCallableThread implements Callable<String>  {
	
	 @Override
	    public String call() throws Exception {
	        System.out.println(Thread.currentThread().getName() + " --> starting...");
	        System.out.println(Thread.currentThread().getName() + " --> stopped...");
	        return "Hello Java";
	    }

}
