package com.vortex.strategy.CD;

public class Test {

	public static void main(String[] args) {
		CDPlayer player = new CDPlayer("步步高");
		player.setCd(new JayCD());
		player.play();
	}

}
