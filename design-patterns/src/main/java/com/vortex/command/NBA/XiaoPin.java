package com.vortex.command.NBA;



public class XiaoPin {

	private ZhaoBenShan zhaoBenShan = new ZhaoBenShan();
	private FanWei fanWei = new FanWei();
	
	public void perform(){
		zhaoBenShan.say();
		fanWei.say();
	}
}
