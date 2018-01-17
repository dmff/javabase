package com.vortex.state.mood;

public class HappyMood implements Mood {

	@Override
	public void perform() {
		System.out.println("今天很开心，啦啦啦啦啦！");
	}

}
