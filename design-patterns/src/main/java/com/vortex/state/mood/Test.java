package com.vortex.state.mood;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		
		Person person = new Person();
		person.addMood("happy", new HappyMood());
		person.addMood("happy2", new HappyMood2());
		person.addMood("sad", new SadMood());
		person.addMood("miserable", new MiserableMood());
		
		person.perform();
	}

}
