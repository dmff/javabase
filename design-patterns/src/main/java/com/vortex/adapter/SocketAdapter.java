package com.vortex.adapter;

/**
 * 插座适配器，使得3孔插座可以使用2孔插座充电
 * 需要实现老接口,并且持有新的接口
 * @author dmf
 *
 */
public class SocketAdapter implements DBSocketInterface{

	private GBSocketInterface gBSocket;
	
	public SocketAdapter(GBSocketInterface gBSocket) {
		super();
		this.gBSocket = gBSocket;
	}




	@Override
	public void powerWith2Round() {
		gBSocket.powerWithThreeFlat();
	}

}
