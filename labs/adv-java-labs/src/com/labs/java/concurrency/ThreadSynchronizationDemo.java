package com.labs.java.concurrency;

public class ThreadSynchronizationDemo {

	public static void main(String[] args) {
		
		System.out.println("Entered into"+Thread.currentThread().getName());
		SequencerThread t1= new SequencerThread();
		t1.setName("T1");
		t1.start();
		
		SequencerThread t2= new SequencerThread();
		t1.setName("T2");
		t2.start();
		
		System.out.println("Exited from"+Thread.currentThread().getName());
	}

}

class SequencerThread extends Thread{

	@Override
	public void run() {
		System.out.println("Entered into"+Thread.currentThread().getName());
		SequenceGenerator sg = new SequenceGenerator();
		sg.increment();
		sg.decrement();
		sg.increment();
		System.out.println("seqid "+sg.getSeqId() +" counter"+sg.getCounter());
		System.out.println("Extied from "+Thread.currentThread().getName());
	}
	
	
}

class SequenceGenerator {
	public  int seqId;
	public static int counter;
	
	//synchronized method
	public synchronized void increment() {
		seqId = seqId+1;
		counter= counter+1;
	}
	
	public void decrement() {
		//synchronized block
		synchronized (this) {
			seqId = seqId-1;
			counter= counter-1;
		}
		
		System.out.println("incremented.....");
	}
	
	public int getSeqId() {
		return seqId ;
	}
	
	public int getCounter() {
		return counter;
	}
}