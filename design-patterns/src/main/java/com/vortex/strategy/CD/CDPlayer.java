package com.vortex.strategy.CD;

public class CDPlayer {

	private String brand;
	private CD cd;
	
	public CDPlayer(String brand) {
		super();
		this.brand = brand;
	}

	public CD getCd() {
		return cd;
	}
	
	public void setCd(CD cd) {
		this.cd = cd;
	}
	
	
	public void play(){
		System.out.println(brand + "牌CD机启动啦。。。" );
		cd.sing();
	}
}
