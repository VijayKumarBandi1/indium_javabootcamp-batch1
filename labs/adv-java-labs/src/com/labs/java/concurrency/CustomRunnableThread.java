package com.labs.java.concurrency;

public class CustomRunnableThread implements Runnable {

	@Override
	public void run() {
		
		System.out.println("Entered into " + Thread.currentThread().getName());
        System.out.println("********************");
        for(int i=0;i<=10;i++) {
        	System.out.println(Thread.currentThread().getName()+" "+i);
        	try {
        		Thread.sleep(1000);
        	}catch (Exception e) {
				System.out.println(e);
			}
        }
        System.out.println("Exited from " + Thread.currentThread().getName());
		
	}

}
