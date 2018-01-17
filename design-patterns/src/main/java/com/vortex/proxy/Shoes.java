package com.vortex.proxy;



public class Shoes {

	private String name;
	private Currency price;
	
	public Shoes(String name, Currency price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Currency getPrice() {
		return price;
	}

	public void setPrice(Currency price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Shoes [name=" + name + ", price=" + price + "]";
	}
	
	
}
