package edu.umb.cs681.hw07;

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
		
		System.out.println("\n\nPrime Factorization of 40:\n");
		RunnableCancellablePrimeFactorizer gen = new 
										RunnableCancellablePrimeFactorizer(40, 2,	(long) Math.sqrt(40));
		
		Thread thread = new Thread(gen);
			
		thread.start();
		gen.setDone();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("Factors are:");
		gen.getPrimeFactors().forEach((Long l) -> System.out.print(l + ", "));
	}
}