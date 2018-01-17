package com.vortex.state.person2;



public interface State {

	public void perform();
	
	
	public State nextState();
}
