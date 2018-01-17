package com.vortex.clone;

import java.util.Date;

public class Sheep implements Cloneable{

	private String name;
	private Date date;
	
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Object object = super.clone();
		//实现深复制,手动实现深复制
		Sheep sheep = (Sheep) object;
		//sheep.date = (Date) this.date;
		sheep.date = new Date();
		return object;
				
	}
	public Sheep(String name, Date date) {
		super();
		this.name = name;
		this.date = date;
	}
	public Sheep() {}
	
	
	@Override
	public String toString() {
		return "Sheep [name=" + name + ", date=" + date + "]";
	}
	
	
}
