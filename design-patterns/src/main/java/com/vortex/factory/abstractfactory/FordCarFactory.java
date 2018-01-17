package com.vortex.factory.abstractfactory;


import com.vortex.factory.staticfactory.Car;
import com.vortex.factory.staticfactory.FordCar;

public class FordCarFactory implements AbstractCarFactory{

	@Override
	public Car getCar() {
		return new FordCar();
	}

}
