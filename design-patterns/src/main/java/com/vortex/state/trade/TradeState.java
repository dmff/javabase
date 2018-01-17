package com.vortex.state.trade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public enum TradeState {


	NORMAL(){

		@Override
		public void perform() {
			System.out.println("交易正在正常运行");
		}
		
	},UPPER_BOUND(){

		@Override
		public void perform() {
			System.out.println("股票涨停,交易受限");
		}
		
	},LOWER_BOUND(){

		@Override
		public void perform() {
			System.out.println("股票跌停，交易受限");
		}
		
	},STOP(){

		@Override
		public void perform() {
			System.out.println("股票停牌，交易暂停");
		}
		
	};
	
	private static volatile int currentPrice;
	static{
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				receivePrice();
			}
			
			private void receivePrice(){
				while(true){
					try {
						BufferedReader reader = new BufferedReader(new FileReader(new File("src/state/trade/price.data")));
						String line = reader.readLine();
						reader.close();
						currentPrice = Integer.parseInt(line);
						Thread.sleep(3000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		thread.setDaemon(true);
		thread.start();
	}
	
	public abstract void perform();
	
	public TradeState nextState(){
		if(currentPrice > 40){
			return UPPER_BOUND;
		}else if(currentPrice >= 10){
			return NORMAL;
		}else if(currentPrice > 0){
			return LOWER_BOUND;
		}else {
			return STOP;
		}
	}
}
