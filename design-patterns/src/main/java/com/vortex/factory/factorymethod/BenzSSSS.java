package com.vortex.factory.factorymethod;


import com.vortex.factory.staticfactory.BenzCar;
import com.vortex.factory.staticfactory.Car;

public class BenzSSSS extends SSSS{

	/**
	 * 可以自己直接创建，也可以通过工厂获得
	 */
	@Override
	public Car getCar() {
		return new BenzCar();
	}

}
