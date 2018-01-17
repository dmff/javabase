package com.vortex.factory.factorymethod;


import com.vortex.factory.staticfactory.Car;

/**
 * 有一个司机需要开车:但是他开什么车目前不知道
 * @author dmf
 *
 */
public abstract class Driver {

	public void driver(){
		getCar().run();
	}
	
	abstract Car getCar();
	
}
