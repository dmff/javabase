package com.vortex.decorator.woman2;


import com.vortex.decorator.woman1.Woman;

public class DressupWoman extends AbstractWoman{

	public DressupWoman(Woman woman) {
		super(woman);
	}
	
	@Override
	public int getBeauty() {
		return super.getBeauty()+20;
	}
}
