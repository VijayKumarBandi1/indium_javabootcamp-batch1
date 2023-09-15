package com.labs.java.concurrency;

public class ThreadWithRunnableDemo {

	public static void main(String[] args) {
		
		System.out.println("Entered into " + Thread.currentThread().getName());

        CustomRunnableThread r1 = new CustomRunnableThread();
        
        for(int i=0;i<=10;i++) {
        	System.out.println(Thread.currentThread().getName()+" "+i);
        	try {
        		Thread.sleep(1000);
        	}catch (Exception e) {
				System.out.println(e);
			}
        }

        Thread t1 = new Thread(r1);
        t1.setName("T1");
        t1.start();

        Thread t2 = new Thread(r1);
        t2.setName("T2");
        t2.start();

        System.out.println("Exited from " + Thread.currentThread().getName());


	}

}
