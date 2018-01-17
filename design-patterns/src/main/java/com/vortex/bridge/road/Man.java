package com.vortex.bridge.road;

public class Man extends Person{

	public Man(AbstractRoad road) {
		super(road);
	}

	@Override
	public void run() {
		System.out.println("男人开着");
		road.run();
	}

}
