package edu.umb.cs681.hw10;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
	
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();

	public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
		// TODO Auto-generated constructor stub
	}

	public void setDone() {
		lock.lock();
		try { done = false; } 
		finally { lock.unlock(); }
	}

	public void generatePrimeFactors() {
		
		long divisor = from;
		while (dividend != 1 && divisor <= to) {
			
			lock.lock();
			try {
				if (done) {	break; }
				if (divisor > 2 && isEven(divisor)) {
					divisor++;
					continue;
				}
				if (dividend % divisor == 0) {
					factors.add(divisor);
					dividend /= divisor;
				} 
				else if (divisor == 2) { divisor++; } 
				else { divisor += 2; }
			} 
			finally { lock.unlock(); }
		}
	}

	public static void main(String[] args) {
		
		//-----------Single Thread ----------------------------------------------------------------
		
		System.out.println("\n\nPrime Factorization of 40	:\n");
		RunnableCancellablePrimeFactorizer rfactor = new 
										RunnableCancellablePrimeFactorizer(40, 2,	(long) Math.sqrt(40));
		
		Thread thread = new Thread(rfactor);
			
		thread.start();
		rfactor.setDone();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("Factors are	:\t\t");
		rfactor.getPrimeFactors().forEach((Long l) -> System.out.print(l + ", "));
		
		//-----------Multi - Thread ----------------------------------------------------------------
		
		System.out.println("\n\nPrime Factorization of 2489	:\n");

		RunnableCancellablePrimeFactorizer rfactor1 = new 
								RunnableCancellablePrimeFactorizer(2489, 2, (long) Math.sqrt(2489) / 2);
		RunnableCancellablePrimeFactorizer rfactor2 = new 
								RunnableCancellablePrimeFactorizer(2489, 1 + (long) Math.sqrt(2489) / 2, (long) Math.sqrt(2489));

		Thread t1 = new Thread(rfactor1);
		Thread t2 = new Thread(rfactor2);
		
		t1.start();
		t2.start();
		
		rfactor1.setDone();
		rfactor2.setDone();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 
		if (rfactor1.getPrimeFactors().isEmpty() ) {		
			Long temp = 2489 / rfactor2.getPrimeFactors().element();
			System.out.println("Since, one set is empty, the complete set of prime numbers can be derived from generated factors");
			System.out.print("Factors are	:\t\t");
			rfactor2.getPrimeFactors().forEach((Long l) -> System.out.print(l + ", "));
			System.out.print(temp);	
		}
		else if (rfactor2.getPrimeFactors().isEmpty() ) {		
			Long temp = 2489 / rfactor1.getPrimeFactors().element();
			System.out.println("Since, one set is empty, the complete set of prime numbers can be derived from generated factors");
			System.out.print("Factors are	:\t\t");
			rfactor1.getPrimeFactors().forEach((Long l) -> System.out.print(l + ", "));
			System.out.print(temp);
		}
		else {		
			System.out.print("Factors are	:\t\t");		
			rfactor1.getPrimeFactors().forEach((Long l) -> System.out.print(l + ", "));
			rfactor2.getPrimeFactors().forEach((Long l) -> System.out.print(l + ", "));
		}
	}
}
