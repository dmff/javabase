package com.vortex.state.person2;


public class Person {

	private State state;
	
	public void setState(State state) {
		this.state = state;
	}
	public State getState() {
		return state;
	}
	
	//如何自动切换״̬
	public void perform(){
		state.perform();
		//这种自动切换比较死板,不符合开闭原则 
		if (state instanceof YougthState) {
			state = new AdultState();
		}else if (state instanceof AdultState) {
			state = new OldState();
		}
	}
}
