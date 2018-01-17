package com.vortex.decorator.woman1;

public class PoliteWoman extends Woman{

	public PoliteWoman(int iQ, int beauty, String name) {
		super(iQ, beauty, name);
	}
	
	@Override
	public void say() {
		System.out.println("大家好...");
		super.say();
		System.out.println("请多多关照...");
	}

}
