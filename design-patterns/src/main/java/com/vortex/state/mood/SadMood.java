package com.vortex.state.mood;

public class SadMood implements Mood {

	@Override
	public void perform() {
		System.out.println("今天很沮丧，呜呜呜呜呜！");
	}

}
