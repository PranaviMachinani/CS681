package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	
	
	HashMap<java.nio.file.Path, Integer> pathMap = new HashMap<java.nio.file.Path, Integer>();
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
		lock.lock();
		try {
			if(pathMap.containsKey(path)) {
				pathMap.put(path, pathMap.get(path)+1);
			}else {
				pathMap.put(path, 1);
			}
			
		}finally {
			lock.unlock();
		}
	}
	
	public int getCount(Path path) {
		
		lock.lock();
		try {
			if(pathMap.containsKey(path)) {
				return pathMap.get(path);
				 
			}
			else {
				return 0;
			}
			
		}finally {
			lock.unlock();
		}
	}
	
	
	
	

}