package com.vortex.command.NBA;




public class WanglihongSingCommand implements Command {

	private WangliHong wanglihong;
	
	public WanglihongSingCommand(WangliHong wanglihong) {
		this.wanglihong = wanglihong;
	}
	
	@Override
	public void excute() {
		wanglihong.sing();
	}

}
