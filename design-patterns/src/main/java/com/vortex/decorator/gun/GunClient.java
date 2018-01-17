package com.vortex.decorator.gun;

public class GunClient {

	public static void main(String[] args) {
		Gun gun1 = new AK47();
		
		Gun gun2 = new FangDouGun(new MiaoZhunGun(new XiaoShenGun(gun1)));
		gun2.aim();
		gun2.shut();
		gun2.load();
	}
}
