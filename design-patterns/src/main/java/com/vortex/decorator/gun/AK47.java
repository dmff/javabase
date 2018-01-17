package com.vortex.decorator.gun;

public class AK47 implements Gun{

	@Override
	public void aim() {
		System.out.println("ak47 瞄准中..........");
	}

	@Override
	public void shut() {
		System.out.println("ak47开枪......");
	}

	@Override
	public void load() {
		System.out.println("ak47装满子弹了，要小心哦....");
	}

	@Override
	public void unload() {
		System.out.println("ak47卸掉子弹了，可以放心了....");
	}

}
