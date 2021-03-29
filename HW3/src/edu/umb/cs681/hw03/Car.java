package edu.umb.cs681.hw03;

import java.util.ArrayList;



public class Car {
	private String model, make;
	private int mileage, year;
	private int price;
	private int dominationCount;

	public Car(String make, String model, int mileage, int year, int price) {
		this.make = make;
		this.model = model;
		this.mileage = mileage;
		this.year = year;
		this.price = price;
	}

	public String getModel() {
		return model;
	}

	public String getMake() {
		return make;
	}

	public int getMileage() {
		return mileage;
	}

	public int getYear() {
		return year;
	}

	public int getPrice() {
		return price;
	}

	public int getDominationCount() {
		return this.dominationCount;
	}

	public void setDominationCount(ArrayList<Car> cars) {
		for (Car car : cars) {
			if ((car.getPrice() >= this.getPrice()) && (car.getMileage() >= this.getMileage())
					&& (car.getYear() <= this.getYear())) {
				this.dominationCount++;
			}
		}
		this.dominationCount--;
	}

	public String toString() {
		return this.make + " " + this.model + " " + this.mileage + " " + this.year + " " + this.price;
	}

	public static void main(String[] args) {
		int z = 0;
		Car tesla=new Car("X","A",25,2018,50000);
		Car honda=new Car("honda","Civic",12,2019,10000);
		Car kia=new Car("kia","B",10,2012,40000);
		ArrayList<Car> cars=new ArrayList<Car>();

		cars.add(tesla);
		cars.add(honda);
		cars.add(kia);
		int max_cost = cars.stream().map((Car car) -> car.getPrice()).reduce(0,
				(result, price) -> result > price ? result : price);
		

		int min_cost = cars.stream().map((Car car) -> car.getPrice()).reduce(1000000000,
				(result, price) -> price > result ? result : price);
	

		int count = cars.stream().map(x -> z + 1).reduce(0, (a, b) -> a + b);
		System.out.println("Max Price : " + max_cost);
		System.out.println("Min Price : " + min_cost);
		System.out.println("Total number of cars : " + count);
	}
}
