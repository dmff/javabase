package com.vortex.command.NBA;


public class Test {

	public static void main(String[] args) {

		NbaGameHandler handler = new NbaGameHandler();
		handler.setA(new BlockCommand());
		handler.setB(new PickAndRollCommand());
		handler.setC(new StealCommand());
		handler.setD(new ShootCommand());
		
		handler.pushA();
		handler.pushB();
		handler.pushC();
		handler.pushD();
	}

}
