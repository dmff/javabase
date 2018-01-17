package com.vortex.decorator.gun;

public abstract class WrapparGun implements Gun{
	private Gun gun;
	
	public WrapparGun(Gun gun) {
		this.gun = gun;
	}

	@Override
	public void aim() {
		gun.aim();
	}

	@Override
	public void shut() {
		gun.shut();
	}

	@Override
	public void load() {
		gun.load();
	}

	@Override
	public void unload() {
		gun.unload();
	}
	
	
}
