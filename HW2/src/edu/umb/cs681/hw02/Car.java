package edu.umb.cs681.hw02;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class Car {

	private String model, make;
	private int mileage, year;
	private float price;
	private int dominationCount;

	public Car(String make, String model, int mileage, int year, float price) {
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

	public float getPrice() {
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
	
          @Override
        public String toString() {
        	
        	return this.make +" "+ this.model+" "+ this.mileage+" "+this.year+" "+this.price;
        }

	public static void main(String[] args) {
		
		
		Car tesla=new Car("X","A",25,2018,50000);
		Car honda=new Car("honda","Civic",12,2019,10000);
		Car kia=new Car("kia","B",10,2012,40000);
		ArrayList<Car> cars=new ArrayList<Car>();

		cars.add(tesla);
		cars.add(honda);
		cars.add(kia);
	
		System.out.println("Sorted by Year:");
		List<Car> sortedByYear=cars.stream().sorted(Comparator.comparingInt(Car::getYear)).collect(Collectors.toList());
		sortedByYear.forEach(System.out::println);
		System.out.println(" \n");
		System.out.println("Sorted by Mileage:");
		List<Car> sortedByMileage=cars.stream().sorted(Comparator.comparingInt(Car::getMileage)).collect(Collectors.toList());
		sortedByMileage.forEach(System.out::println);
		System.out.println(" \n");
		System.out.println("Sorted by Price:");
		List<Car> sortedByPrice=cars.stream().sorted(Comparator.comparingDouble(Car::getPrice)).collect(Collectors.toList());
		sortedByPrice.forEach(System.out::println);
		System.out.println(" \n");
		System.out.println("Sorted Domination Count:");
		List<Car> sortedByDomCount=cars.stream().sorted(Comparator.comparingInt(Car::getDominationCount)).collect(Collectors.toList());
		sortedByDomCount.forEach(System.out::println);
		

	}
}