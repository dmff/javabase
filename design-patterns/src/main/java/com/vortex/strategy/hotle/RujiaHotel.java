package com.vortex.strategy.hotle;

public class RujiaHotel extends AbstractHotel {
	
	public RujiaHotel() {
		/**
		 * 下面的这5个参数的设置可以从配置文件读取，可以把每一个Hotel的具体加个策略放在一个配置文件中加载
		 */
		super("如家",3);
		getRoomFeeStrategy().weekdayOrdinaryPrice(160);
		getRoomFeeStrategy().weekdayVipPrice(110);
		getRoomFeeStrategy().weekendsOrdinaryPrice(60);
		getRoomFeeStrategy().weekendsVipPrice(50);
	}
}
