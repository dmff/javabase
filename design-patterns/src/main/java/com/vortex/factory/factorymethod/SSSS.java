package com.vortex.factory.factorymethod;


import com.vortex.factory.staticfactory.Car;

public abstract class SSSS {

	/**
	 * 具体卖的车型由子类确定，类加载机制，运行时加载实现多态
	 * @return
	 */
	public Car sellCar(){
		return getCar();
	}
	
	public abstract Car getCar();
	
}
