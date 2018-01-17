package com.vortex.decorator.woman1;

public class DressupWoman extends Woman{

	public DressupWoman(int iQ, int beauty, String name) {
		super(iQ, beauty, name);
	}
	
	@Override
	public int getBeauty() {
		return super.getBeauty()+20;
	}

}
