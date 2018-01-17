package com.vortex.factory.factorymethod;


import com.vortex.factory.staticfactory.Car;

public class CarTest {

	public static void main(String[] args) {
		SSSS ssss = new BenzSSSS();
		Car car = ssss.sellCar();
		car.run();	
	}
}
