package com.vortex.strategy.hotle;

import java.util.Calendar;

public abstract class AbstractHotel implements Hotel{

	private int level;
	
	private String name;
	
	private RoomFeeStrategy roomFeeStrategy = new RoomFeeStrategy();
	
	public AbstractHotel(String name,int level){
		this.name = name;
		this.level = level;
	}

	@Override
	public int level() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public RoomFeeStrategy getRoomFeeStrategy() {
		return roomFeeStrategy;
	}
	
	@Override
	public int fee(Calendar start, Calendar end, boolean isVip) {
		return roomFeeStrategy.fee(start, end, isVip);
	}

	@Override
	public String toString() {
		return "Hotel [level=" + level + ", name=" + name + "]";
	}
	
	
}
