package com.vortex.factory.abstractfactory;


import com.vortex.factory.staticfactory.BenzCar;
import com.vortex.factory.staticfactory.Car;

public class BenzCarFactory implements AbstractCarFactory{

	@Override
	public Car getCar() {
		return new BenzCar();
	}

}
