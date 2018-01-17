package com.vortex.bridge.road;

public class Test {

	public static void main(String[] args) {
		//AbstractRoad road = new SpeedWay(new Bus());
		//road.run();
		
		Person person = new Man(new Street(new LittleCar()));
		person.run();
	}
}
