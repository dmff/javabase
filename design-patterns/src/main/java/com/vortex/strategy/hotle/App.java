package com.vortex.strategy.hotle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws ParseException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入入住时间，退房时间，是否是会员，中间用逗号隔开（如：2009-11-11,2009-12-10,true）：");
		String line = scanner.nextLine();
		String[] strs = line.split("\\,");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = strs[0];
		String endDate = strs[1];
		boolean isVip = Boolean.valueOf(strs[2]);
		Calendar start = Calendar.getInstance();
		start.setTime(dateFormat.parse(startDate));
		Calendar end = Calendar.getInstance();
		end.setTime(dateFormat.parse(endDate));
		
		
		HotelContext context = new HotelContext();
		Hotel perfectHotel = context.perfectHotel(start, end, isVip);
		System.out.println("完美的酒店是：" + perfectHotel);
		scanner.close();
	}

}
