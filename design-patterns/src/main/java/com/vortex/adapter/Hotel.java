package com.vortex.adapter;

public class Hotel {

	//旅馆有一个2孔插座
	private DBSocketInterface dbSocket;

	public void setDbSocket(DBSocketInterface dbSocket) {
		this.dbSocket = dbSocket;
	}
	
	public Hotel() {}

	public Hotel(DBSocketInterface dbSocket) {
		super();
		this.dbSocket = dbSocket;
	}
	
	//使用插座充电的功能
	public void charge(){
		dbSocket.powerWith2Round();
	}
}
