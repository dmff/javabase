package com.vortex.state.person2;


public class OldState implements State {

	@Override
	public void perform() {
		System.out.println("我是老年人，我要安享晚年...");
	}

	@Override
	public State nextState() {
		//return null;
		return new YougthState(); //就会成环状״
	}

}
