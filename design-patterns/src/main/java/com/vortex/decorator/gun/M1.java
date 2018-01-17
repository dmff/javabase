package com.vortex.decorator.gun;

public class M1 implements Gun{

	@Override
	public void aim() {
		System.out.println("m1 瞄准中..........");
	}

	@Override
	public void shut() {
		System.out.println("m1开枪......");
	}

	@Override
	public void load() {
		System.out.println("m1装满子弹了，要小心哦....");
	}

	@Override
	public void unload() {
		System.out.println("m1卸掉子弹了，可以放心了....");
	}

}
