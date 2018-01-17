package com.vortex.bridge.road;

public abstract class Person {
	
	protected  AbstractRoad road;
	
	public Person(AbstractRoad road) {
		super();
		this.road = road;
	}
	
	public abstract void run();
}
