package com.vortex.strategy.hotle;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RoomFeeStrategy implements FeeStrategy {

	public static final String WEEKDAY_ORDINARY = "weekday_ordinary";
	public static final String WEEKDAY_VIP = "weekday_vip";
	public static final String WEEKENDS_ORDINARY = "weekends_ordinary";
	public static final String WEEKENDS_VIP = "weekends_vip";
	
	private Map<String,Integer> priceRule = new HashMap<String,Integer>();
	
	public void weekdayOrdinaryPrice(int price){
		priceRule.put(WEEKDAY_ORDINARY, price);
	}

	public void weekdayVipPrice(int price){
		priceRule.put(WEEKDAY_VIP, price);
	}
	
	public void weekendsOrdinaryPrice(int price){
		priceRule.put(WEEKENDS_ORDINARY, price);
	}
	
	public void weekendsVipPrice(int price){
		priceRule.put(WEEKENDS_VIP, price);
	}


	@Override
	public int fee(Calendar start, Calendar end,boolean isVip) {
		int fee = 0;
		Calendar current = start;
		end.add(Calendar.DAY_OF_MONTH, 1);
		while(current.before(end)){
			int day = current.get(Calendar.DAY_OF_WEEK);
			if(day >= 0 && day <= 4){
				if(isVip){
					fee += priceRule.get(WEEKDAY_VIP);
				}else{
					fee += priceRule.get(WEEKDAY_ORDINARY);
				}
			}else{
				if(isVip){
					fee += priceRule.get(WEEKENDS_VIP);
				}else{
					fee += priceRule.get(WEEKENDS_ORDINARY);
				}
			}
			current.add(Calendar.DAY_OF_MONTH, 1);
		}
		return fee;
	}

}
