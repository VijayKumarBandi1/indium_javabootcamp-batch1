package com.labs.java.concurrency;

public class ThreadWithExtendDemo {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Entered into " + Thread.currentThread().getName());

        CustomThread t1 = new CustomThread();
        t1.setName("T1");
//        t1.run();
        // Register and submits the thread
        t1.start();

        CustomThread t2 = new CustomThread();
        t2.sleep(5*60*1000);
        t2.setName("T2");
        // Register and submits the thread
        t2.start();

        System.out.println("Exited from " + Thread.currentThread().getName());
	}

}
