package com.vortex.bridge.road;

public class SpeedWay extends AbstractRoad {
	
	public SpeedWay(Car car) {
		super(car);
	}

	@Override
	public void run() {
		car.run();
		System.out.println("在高速公路上开");
	}

}
