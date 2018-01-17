package com.vortex.command.tv;


public class CommandChange implements Command{
	private Tv myTv;
	
	private int channel;
	
	public CommandChange(Tv myTv,int channel) {
		//super();
		this.myTv = myTv;
		this.channel = channel;
	}

	@Override
	public void execute() {
		myTv.changeChannel(channel);
	}

}
