package com.vortex.decorator.gun;

public class FangDouGun extends WrapparGun{

	public FangDouGun(Gun gun) {
		super(gun);
	}
	
	@Override
	public void shut() {
		super.shut();
		System.out.println("加了防抖装置，射击精度提升30%");
	}

}
