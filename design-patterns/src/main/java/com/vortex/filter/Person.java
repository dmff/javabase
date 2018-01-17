package com.vortex.filter;

public class Person {

	private String name;
	private String sex;
	private String marryStatus;
	
	public Person(String name, String sex, String marryStatus) {
		super();
		this.name = name;
		this.sex = sex;
		this.marryStatus = marryStatus;
	}
		
	@Override
	public String toString() {
		return "Person [name=" + name + ", sex=" + sex + ", marryStatus=" + marryStatus + "]";
	}

	public String getName() {
		return name;
	}
	public String getSex() {
		return sex;
	}
	public String getMarryStatus() {
		return marryStatus;
	}
	
	
}
