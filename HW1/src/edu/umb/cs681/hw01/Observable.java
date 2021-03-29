package edu.umb.cs681.hw01;

import java.util.LinkedList;

public abstract class Observable {
	protected boolean changed;
	protected LinkedList<Observer> obs = new LinkedList<Observer>();

	public void addObserver(Observer o) {
		if (!obs.contains(o)) {
			obs.add(o);
		}
	}

	public void deleteObserver(Observer o) {
		if (obs.contains(o)) {
			obs.remove(o);
		}
	}

	protected void setChanged() {
		changed = true;
	}
	
	protected int countObserver() {
		return obs.size();
	}
	
	public boolean hasChanged() {
		return changed;
	}

	protected void clearChanged() {
		changed = false;
	}

	public void notifyObservers(Object object) {
		if (hasChanged()) {
			obs.forEach((Observer obs) -> obs.update(this, object));
			clearChanged();
		}
	}
}