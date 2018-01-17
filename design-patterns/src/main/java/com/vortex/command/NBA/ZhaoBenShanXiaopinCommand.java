package com.vortex.command.NBA;




public class ZhaoBenShanXiaopinCommand implements Command {

	private XiaoPin xiaoPin;
	
	public ZhaoBenShanXiaopinCommand(XiaoPin xiaoPin) {
		super();
		this.xiaoPin = xiaoPin;
	}


	public void excute() {
		xiaoPin.perform();
	}

}
