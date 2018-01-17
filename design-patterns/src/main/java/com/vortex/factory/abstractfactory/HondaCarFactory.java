package com.vortex.factory.abstractfactory;


import com.vortex.factory.staticfactory.Car;
import com.vortex.factory.staticfactory.HondaCar;

public class HondaCarFactory implements AbstractCarFactory{

	@Override
	public Car getCar() {
		return new HondaCar();
	}

}
