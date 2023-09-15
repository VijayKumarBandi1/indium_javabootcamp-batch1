package com.labs.java.concurrency;

public class JoinThreadDemo {

	public static void main(String[] args) {
        System.out.println("Entered into " + Thread.currentThread().getName());

        CustomRunnableThread r1 = new CustomRunnableThread();

        Thread t1 = new Thread(r1);
        t1.setName("T1");
        t1.start();
        try {
            // ensures underlying thread (main) waits until the current thread (T1) completes
            t1.join();
            t1.interrupt();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        Thread t2 = new Thread(r1);
        t2.setName("T2");
        t2.start();
        try {
            // ensures underlying thread (main) waits until the current thread (T2) completes
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        for(int i=1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Exited from " + Thread.currentThread().getName());
    }
}
