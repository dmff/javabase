package com.vortex.factory.abstractfactory;


import com.vortex.factory.staticfactory.Car;

public class Customer {

	private Car car;
	
	public Car getCar() {
		return car;
	}
	
	//顾客买车
	public void buyCar(SSSS ssss){
		car = ssss.sellCar();
	}
	
	public void driver(){
		car.run();
	}
}
