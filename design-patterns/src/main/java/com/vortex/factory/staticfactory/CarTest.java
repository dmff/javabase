package com.vortex.factory.staticfactory;

public class CarTest {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		//Car car = CarFactory1.getCar("benz");	
		Car car = CarFactory2.getCar();
		Family family = new Family(car);
		family.goOut();
	}
}
