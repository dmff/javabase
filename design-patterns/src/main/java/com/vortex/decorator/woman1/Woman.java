package com.vortex.decorator.woman1;

public class Woman {

	private int IQ;
	private int beauty;
	private String name;
	
	public Woman(int iQ, int beauty, String name) {
		super();
		IQ = iQ;
		this.beauty = beauty;
		this.name = name;
	}
	
	
	public void say(){
		System.out.println("我叫"+name);
	}
	
	public int getIQ() {
		return IQ;
	}
	public void setIQ(int iQ) {
		IQ = iQ;
	}
	public int getBeauty() {
		return beauty;
	}
	public void setBeauty(int beauty) {
		this.beauty = beauty;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Woman [IQ=" + IQ + ", beauty=" + beauty + ", name=" + name + "]";
	}
	
	
	
}
