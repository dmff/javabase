package com.vortex.bridge.road;

public class Street extends AbstractRoad {

	public Street(Car car) {
		super(car);
	}

	@Override
	public void run() {
		car.run();
		System.out.println("在街道上开...");
	}

}
