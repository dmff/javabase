package com.vortex.bridge.road;

public abstract class AbstractRoad {
	
	protected Car car;
	
	public AbstractRoad(Car car) {
		super();
		this.car = car;
	}
	
	public abstract void run();
}
