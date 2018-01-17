package com.vortex.command.tv;

public class CommandOn implements Command{

	//打开电视的命令,对着电视遥控
	
	private Tv myTv;
	
	public CommandOn(Tv myTv) {
		super();
		this.myTv = myTv;
	}

	@Override
	public void execute() {
		myTv.turnOn();
	}

}
