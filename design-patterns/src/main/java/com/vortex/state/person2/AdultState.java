package com.vortex.state.person2;



public class AdultState implements State {

	@Override
	public void perform() {
		System.out.println("我是成年人，我要努力工作... ");
	}

	@Override
	public State nextState() {
		return new OldState();
	}

}
