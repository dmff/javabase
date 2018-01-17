package com.vortex.state.person3;

public class Test {

	public static void main(String[] args) {
		
		Person person = new Person();
		person.setState(State.TEEN);
		person.perform();
		person.perform();
		person.perform();
		person.perform();
	}
}
