package com.vortex.factory.staticfactory;

public class Family {

	private Car car;
	
	public void setCar(Car car) {
		this.car = car;
	}

	public Family(Car car) {
		super();
		this.car = car;
	}
	
	public void goOut(){
		System.out.println("全家出游...");
		car.run();
		System.out.println("玩的开心哦...");
	}
	
}
