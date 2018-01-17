package com.vortex.factory.factorymethod;


import com.vortex.factory.staticfactory.Car;
import com.vortex.factory.staticfactory.FordCar;

public class DiaoSi extends Driver{

	/**
	 * 屌丝有一辆福特汽车
	 */
	
	@Override
	Car getCar() {
		return new FordCar();
	}

}
