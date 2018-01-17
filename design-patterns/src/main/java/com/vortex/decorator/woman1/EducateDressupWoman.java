package com.vortex.decorator.woman1;

public class EducateDressupWoman extends DressupWoman{

	public EducateDressupWoman(int iQ, int beauty, String name) {
		super(iQ, beauty, name);
	}
	
	@Override
	public int getIQ() {
		return super.getIQ()+20;
	}
}
