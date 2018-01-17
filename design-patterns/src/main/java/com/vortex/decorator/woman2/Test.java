package com.vortex.decorator.woman2;


import com.vortex.decorator.woman1.Woman;

public class Test {

	public static void main(String[] args) {
		Woman woman = new Woman(60, 60, "凤姐");
		DressupWoman dressupWoman = new DressupWoman(woman);
		System.out.println(dressupWoman.getBeauty());
	}
}
