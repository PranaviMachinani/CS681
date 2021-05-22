package edu.umb.cs681.hw15;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class AdmissionMonitor {

	private int currentVisitors = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition subMaxOccupancyCondition = lock.newCondition();

	private static final int MAX_OCCUPANCY = 5;

	public void enter() {
		lock.lock();

		try {
			while (currentVisitors >= MAX_OCCUPANCY) {
				try {
					System.out.printf("\tAdmissionMonitor.enter (thread %d):  visitors.\n",
							Thread.currentThread().getId());
					subMaxOccupancyCondition.await();
				} catch (InterruptedException e) {
					System.out.printf("\t (thread %d): interrupted!\n",
							Thread.currentThread().getId());
					return;
				}
			}
			currentVisitors++;
			System.out.printf("\tAdmissionMonitor.entrance (thread %d): success! visitors now  %d\n",
					Thread.currentThread().getId(), currentVisitors);
		} finally {
			lock.unlock();

		}
	}

	public void exit() {
		lock.lock();

		try {
			currentVisitors--;
			System.out.printf("\tAdmissionMonitor.exit (thread %d): success! visitors  now %d\n",
					Thread.currentThread().getId(), currentVisitors);

			subMaxOccupancyCondition.signalAll();
		} finally {
			lock.unlock();

		}

	}

	public int countCurrentVisitors() {
		lock.lock();
		try {
			return currentVisitors;
		} finally {
			lock.unlock();
		}
	}

}
