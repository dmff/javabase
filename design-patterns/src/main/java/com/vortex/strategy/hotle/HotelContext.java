package com.vortex.strategy.hotle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class HotelContext {

	private List<Hotel> hotelList = new ArrayList<Hotel>();
	public HotelContext() {
		hotelList.addAll(Arrays.asList(new SevenDayHotel(),new RujiaHotel(),new HantingHotel()));
	}
	
	public Hotel perfectHotel(Calendar start,Calendar end,boolean isVip){
		Hotel perfectHotel = hotelList.get(0);
		for (int i = 1;i < hotelList.size();i++) {
			Hotel hotel = hotelList.get(i);
			int fee = hotel.fee(start, end, isVip);
			int level = hotel.level();
			int perfectFee = perfectHotel.fee(start, end, isVip);
			int perfectLevel = perfectHotel.level();
			if(fee < perfectFee || (fee == perfectFee && level > perfectLevel)){
				perfectHotel = hotel;
			}
		}
		return perfectHotel;
	}
}
