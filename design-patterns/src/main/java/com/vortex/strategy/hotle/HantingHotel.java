package com.vortex.strategy.hotle;

public class HantingHotel extends AbstractHotel {
	
	public HantingHotel() {
		/**
		 * 下面的这5个参数的设置可以从配置文件读取，可以把每一个Hotel的具体加个策略放在一个配置文件中加载
		 */
		super("汉庭",5);
		getRoomFeeStrategy().weekdayOrdinaryPrice(220);
		getRoomFeeStrategy().weekdayVipPrice(100);
		getRoomFeeStrategy().weekendsOrdinaryPrice(150);
		getRoomFeeStrategy().weekendsVipPrice(90);
	}
}
