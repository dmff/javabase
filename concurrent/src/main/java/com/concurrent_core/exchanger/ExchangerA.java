package com.concurrent_core.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerA extends Thread{

	private Exchanger<String> exchanger;

	public ExchangerA(Exchanger<String> exchanger) {
		super();
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		try {
			System.out.println("在线程A中得到线程B的值="+exchanger.exchange("中国人A"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.run();
	};
	
	
	
}
