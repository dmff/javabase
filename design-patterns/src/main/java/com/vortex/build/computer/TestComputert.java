package com.vortex.build.computer;



public class TestComputert {

	public static void main(String[] args) {
		ComputerBuilder builder = new ComputerBuilder();
		ComputerBuilder.Computer computer = builder.setOs("linux").build();
		System.out.println(computer);
	}
}
