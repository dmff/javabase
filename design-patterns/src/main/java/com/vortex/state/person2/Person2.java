package com.vortex.state.person2;


public class Person2 {

	private State state;
	
	public void setState(State state) {
		this.state = state;
	}
	public State getState() {
		return state;
	}
	//有效的自动切换状态̬
	public void perform(){
		if (state !=null) {
			state.perform();
		    state = state.nextState();	
		}	
	}
}
