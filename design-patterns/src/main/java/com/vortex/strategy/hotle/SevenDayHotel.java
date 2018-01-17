package com.vortex.strategy.hotle;

public class SevenDayHotel extends AbstractHotel {
	
	public SevenDayHotel() {
		/**
		 * 下面的这4个参数的设置可以从配置文件读取，可以把每一个Hotel的具体加个策略放在一个配置文件中加载
		 */
		super("七天",4);
		getRoomFeeStrategy().weekdayOrdinaryPrice(110);
		getRoomFeeStrategy().weekdayVipPrice(80);
		getRoomFeeStrategy().weekendsOrdinaryPrice(90);
		getRoomFeeStrategy().weekendsVipPrice(80);
	}
}
