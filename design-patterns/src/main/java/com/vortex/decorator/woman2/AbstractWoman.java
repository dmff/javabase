package com.vortex.decorator.woman2;


import com.vortex.decorator.woman1.Woman;

public abstract class AbstractWoman extends Woman {

	private Woman woman;
	
	public AbstractWoman(Woman woman) {
		super(woman.getIQ(), woman.getBeauty(), woman.getName());
		this.woman = woman;
	}

	@Override
	public void say() {
		woman.say();
	}

	@Override
	public int getIQ() {
		return woman.getIQ();
	}

	@Override
	public int getBeauty() {
		return woman.getBeauty();
	}

	@Override
	public String getName() {
		return woman.getName();
	}

	

	
}
