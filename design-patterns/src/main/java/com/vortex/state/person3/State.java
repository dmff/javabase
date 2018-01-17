package com.vortex.state.person3;

public enum State {
	
	TEEN(){

		@Override
		public void perform() {
			System.out.println("我是小屁孩，好好学习天天向上！");
		}

		@Override
		public State nextState() {
			return YOUTH;
		}
		
	},YOUTH(){

		@Override
		public void perform() {
			System.out.println("我是年轻人，我是奋斗的一族，我对未来充满了希望");
		}

		@Override
		public State nextState() {
			return ELD;
		}
		
	},ELD(){

		@Override
		public void perform() {
			System.out.println("我是老年人了，我不再工作了，我要安享晚年！");
		}

		@Override
		public State nextState() {
			return TEEN;
		}
		
	};
	
	public abstract void perform();
	public abstract State nextState();
	
}
