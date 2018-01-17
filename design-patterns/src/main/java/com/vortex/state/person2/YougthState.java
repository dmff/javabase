package com.vortex.state.person2;


public class YougthState implements State {

	@Override
	public void perform() {
		System.out.println("我是青年人，我要好好努力学习...");
	}

	@Override
	public State nextState() {
		return new AdultState();
	}

}
