package com.vortex.factory.abstractfactory;


import com.vortex.factory.staticfactory.Car;

public class SSSS {

	private AbstractCarFactory carFactory;

	public AbstractCarFactory getCarFactory() {
		return carFactory;
	}

	public void setCarFactory(AbstractCarFactory carFactory) {
		this.carFactory = carFactory;
	}
	
	//4s店的卖车方法
	public Car sellCar(){
		return carFactory.getCar();
	}
}
