package com.vortex.state.trade;

public class TradeCenter {

	private TradeState state;
	
	public void setState(TradeState state) {
		this.state = state;
	}
	
	public TradeState getState() {
		return state;
	}
	
	
	public void operate(){
		while(true){
			state.perform();
			state = state.nextState();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		
		TradeCenter tradeCenter = new TradeCenter();
		tradeCenter.setState(TradeState.NORMAL);
		tradeCenter.operate();
	}
}
