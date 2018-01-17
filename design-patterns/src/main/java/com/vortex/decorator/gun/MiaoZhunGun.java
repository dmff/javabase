package com.vortex.decorator.gun;

public class MiaoZhunGun extends WrapparGun{

	public MiaoZhunGun(Gun gun) {
		super(gun);
	}

	@Override
	public void aim() {
		super.aim();
		System.out.println("加了瞄准镜，命中几率提高50%");
	}
}
