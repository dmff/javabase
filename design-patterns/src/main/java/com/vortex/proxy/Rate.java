package com.vortex.proxy;

public class Rate {

	public static double getRMBRate(){
		return Double.parseDouble(String.format("%.2f", 7.2 - 0.4 * Math.random()));
	}
	
	public static double getYenRate(){
		return Double.parseDouble(String.format("%.2f", 120.0 - 40 * Math.random()));
	}
}
