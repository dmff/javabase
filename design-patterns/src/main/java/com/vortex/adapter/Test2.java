package com.vortex.adapter;

public class Test2 {

	public static void main(String[] args) {
		
		//带的是三孔插头的充电器
		GBSocketInterface gbSocket =new GBSocket();
		
		//旅馆里面只有两孔插座
		Hotel hotel = new Hotel();
		
		hotel.setDbSocket(new SocketAdapter(gbSocket));
		
		hotel.charge();
	}
}
