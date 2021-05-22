package edu.umb.cs681.hw12;

import java.util.concurrent.locks.ReentrantLock;

public class Customer{
    private Address address;
    ReentrantLock lock = new ReentrantLock();

    public Customer(Address addr){
			this.address = addr;
    }
    public Address setAddress(Address addr){
    	lock.lock();
		try {
			this.address = addr;
		}finally {
			lock.unlock();
		}
		return addr;
    }

    public Address getAddress(){
    	lock.lock();
		try {
            return address;
	    }finally {
			lock.unlock();
		}

    }
    
    
}