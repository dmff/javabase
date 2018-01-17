package com.vortex.decorator.woman1;

public class EducateWoman extends Woman{

	public EducateWoman(int iQ, int beauty, String name) {
		super(iQ, beauty, name);
	}

	@Override
	public int getIQ() {
		return super.getIQ()+20;
	}
}
