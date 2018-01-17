package com.vortex.proxy;

public class NikeRetailCustomer implements NikeCustomer{

	public static enum Location{
		HUBEI,BEIJING,JAPAN;
	}
	
	private Location location;
	private double discount;
	
	public NikeRetailCustomer(Location location, double discount) {
		super();
		this.location = location;
		this.discount = discount;
	}

	public Location getLocation() {
		return location;
	}

	@Override
	public double getDiscount() {
		return discount;
	}

}
