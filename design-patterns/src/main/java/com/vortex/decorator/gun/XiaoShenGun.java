package com.vortex.decorator.gun;

public class XiaoShenGun extends WrapparGun{

	public XiaoShenGun(Gun gun) {
		super(gun);
	}

	@Override
	public void shut() {
		super.shut();
		System.out.println("加了消声器，开枪声音分贝降低80%");
	}

}
