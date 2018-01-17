package com.vortex.state.trade;

import java.io.FileWriter;
import java.util.Random;

public class PriceCenter {

	public void providePrice(){
		Thread thread = new Thread(new Runnable() {
			
			private Random random = new Random();
			
			@Override
			public void run() {
				writePrice();
			}
			
		
			private void writePrice(){
				while(true){
					int price = random.nextInt(51);
					try {
						FileWriter writer = new FileWriter("src/state/trade/price.data",false);
						writer.write("" + price);
						writer.flush();
						writer.close();
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
	
	public static void main(String[] args) throws InterruptedException {
		new PriceCenter().providePrice();
		Thread.sleep(Long.MAX_VALUE);
	}
}
