package com.vortex.bridge.computer;

public abstract class Computer {

	private Brand brand;
	private Kind kind;
	
	public Computer(Brand brand, Kind kind) {
		super();
		this.brand = brand;
		this.kind = kind;
	}

	public void sell(){
		System.out.println("卖了一台"+brand.brandName()+kind.kindName()+"电脑");
	}
	
}
