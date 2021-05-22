package edu.umb.cs681.hw17;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AccessCounter {
	
	
	ConcurrentHashMap<java.nio.file.Path, AtomicInteger> pathMap = new ConcurrentHashMap<java.nio.file.Path, AtomicInteger>();
	private ReentrantLock lock = new ReentrantLock();
	private static ReentrantLock staticLock = new ReentrantLock();
	private static AccessCounter instance = null;
	private AccessCounter() {};
	
	
	
	public static AccessCounter getInstance() {
		staticLock.lock();
		try {
			
			if(instance == null) {
				 instance = new AccessCounter();
			}
			 return instance;
			 
		}finally {
			staticLock.unlock();
		}
		
		
	}
	
	public void increment(Path path) {
		pathMap.compute(path, (java.nio.file.Path k, AtomicInteger v) -> {
			if(v == null) {
				System.out.println(Thread.currentThread().getName() + " increase " + path + " to " + 1);
				return new AtomicInteger(1);
			} else {
				System.out.println(Thread.currentThread().getName() + " increase " + path + " to " + (v.get()+1));
				return new AtomicInteger(v.incrementAndGet());
			}
		});
	}
	
	public int getCount(Path path) {
		
		return pathMap.compute(path, (java.nio.file.Path k, AtomicInteger v) -> {
			if(v == null) {
				System.out.println(Thread.currentThread().getName() + " get " + path + " count " + 0);
				return new AtomicInteger(0);
			} else {
				System.out.println(Thread.currentThread().getName() + " get " + path + " count " + v.get());
				return v;
			}
		}).get();
	}
	
	
	
	

}