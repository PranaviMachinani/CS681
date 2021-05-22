package edu.umb.cs681.hw12;

public class CustomerRunnable implements Runnable {
	public void run() {
		Customer c = new Customer(new Address("abc","Boston","MA",1234));

        System.out.println("Customer's Current Address: " + c.getAddress());
		c.setAddress(c.getAddress().change("efg", "Lowell", "MA", 9999));
		System.out.println("Customer's New Address: "+ c.getAddress());
    }
    
    public static void main(String args[]) {
        
        Thread t1 = new Thread(new CustomerRunnable());

        
        t1.start();

        try {
        	t1.join(); 

        }catch(InterruptedException e) {
        	e.printStackTrace();
        }
    }
}
