package com.vortex.decorator.woman2;


import com.vortex.decorator.woman1.Woman;

public class EducateWoman extends AbstractWoman{

	public EducateWoman(Woman woman) {
		super(woman);
	}

	@Override
	public int getIQ() {
		return super.getIQ()+20;
	}
}
