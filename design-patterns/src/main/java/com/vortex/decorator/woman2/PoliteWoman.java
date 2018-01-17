package com.vortex.decorator.woman2;


import com.vortex.decorator.woman1.Woman;

public class PoliteWoman extends AbstractWoman{

	public PoliteWoman(Woman woman) {
		super(woman);
	}

	@Override
	public void say() {
		System.out.println("大家好...");
		super.say();
		System.out.println("请多多关照....");
	}
}
