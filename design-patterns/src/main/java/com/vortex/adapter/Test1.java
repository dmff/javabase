package com.vortex.adapter;

public class Test1 {

	public static void main(String[] args) {
		Hotel hotel = new Hotel(new DBSocket());
		hotel.charge();
	}
}
