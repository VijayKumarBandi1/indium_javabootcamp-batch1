package com.labs.java.sealed;

public class SealedDemo {
	
	public static void main(String[] args) {
		
		Vehicle ve = new Truck();
		ve.showDetails();
	}

}

abstract sealed class Vehicle permits Car,Truck{
	void showDetails() {
		System.out.println("Vehicle");
	}
}

non-sealed class  Car extends Vehicle{

	void showDetails() {
		System.out.println("Car");
	}
}

final class Truck extends Vehicle{

	void showDetails() {
		System.out.println("Truck");
	}
	
}

class Ship {

	void showDetails() {
		System.out.println("Ship");
	}
}

class Animal {

	void showDetails() {
		System.out.println("Animal");
	}
}
