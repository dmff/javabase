package com.vortex.state.mood;

public class MiserableMood implements Mood {

	@Override
	public void perform() {
		System.out.println("今天很痛苦，没法活了！");
	}

}
