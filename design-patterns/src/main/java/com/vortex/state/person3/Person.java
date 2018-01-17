package com.vortex.state.person3;

public class Person {

	private State state;
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	
	public void perform(){
		if(state != null){
			state.perform();
			state = state.nextState();
		}
	}
}
