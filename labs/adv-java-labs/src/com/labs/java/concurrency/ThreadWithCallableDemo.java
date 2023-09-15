package com.labs.java.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadWithCallableDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService es = Executors.newFixedThreadPool(3);
		
		CustomCallableThread c1 = new CustomCallableThread();
        CustomCallableThread c2 = new CustomCallableThread();
        CustomCallableThread c3 = new CustomCallableThread();
		Future<String> f1 = es.submit(c1);
        Future<String> f2 = es.submit(c2);
        Future<String> f3 = es.submit(c3);
		
		//while(f1.isDone()) {
			try {
				 System.out.println(f1.get());
	                System.out.println(f2.get());
	                System.out.println(f3.get());
			}catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
		//}

	}
	

}
