package com.vortex.decorator.woman1;

public class Test {

	public static void main(String[] args) {
		//Woman woman = new Woman(80, 80, "baby");
		//woman.say();
		
		DressupWoman woman = new DressupWoman(80, 80, "baby");
		woman.say();
		System.out.println(woman.getBeauty());
	}
}
