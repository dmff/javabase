package com.vortex.clone;

import java.io.IOException;

public class TestPrototype {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Prototype prototype = new Prototype();
		prototype.setName("dmf");
		
		System.out.println(prototype);
		
		
		
		Prototype deepClone = (Prototype) prototype.deepClone();
		System.out.println(deepClone);
		
		prototype.setName("zyy");
	}
}
