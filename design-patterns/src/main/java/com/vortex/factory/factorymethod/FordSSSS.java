package com.vortex.factory.factorymethod;


import com.vortex.factory.staticfactory.Car;
import com.vortex.factory.staticfactory.FordCar;

public class FordSSSS extends SSSS{

	@Override
	public Car getCar() {
		return new FordCar();
	}

}
