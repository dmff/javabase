package com.vortex.bridge.computer;

public class ComputerTest {

	public static void main(String[] args) {
		Mac mac = new Mac(new MacBrand(), new MacKind());
		mac.sell();
	}
}
