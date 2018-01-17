package com.vortex.bridge.road;

public class Woman extends Person{

	public Woman(AbstractRoad road) {
		super(road);
	}

	@Override
	public void run() {
		System.out.println("女人开着");
		road.run();
	}

}
