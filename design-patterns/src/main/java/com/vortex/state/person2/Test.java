package com.vortex.state.person2;

public class Test {

	public static void main(String[] args) {
		//Person person = new Person();
		Person2 person = new Person2();
		person.setState(new YougthState());
		
		//这里会出现一个问题，第二轮出现的对象会和第一轮出现的对象相等
		person.perform();
		person.perform();
		person.perform();
		person.perform();
	}
}
