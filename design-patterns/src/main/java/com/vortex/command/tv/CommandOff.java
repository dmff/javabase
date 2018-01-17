package com.vortex.command.tv;


public class CommandOff implements Command{
	private Tv myTv;
	
	public CommandOff(Tv myTv) {
		//super();
		this.myTv = myTv;
	}

	@Override
	public void execute() {
		myTv.turnOff();
	}

}
