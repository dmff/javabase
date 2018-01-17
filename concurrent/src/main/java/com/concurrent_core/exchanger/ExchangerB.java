package com.concurrent_core.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerB extends Thread{

	private Exchanger<String> exchanger;

	public ExchangerB(Exchanger<String> exchanger) {
		super();
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		try {
			System.out.println("在线程B中得到线程A的值="+exchanger.exchange("中国人B"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.run();
	};
	
	public static void main(String[] args) {
		Exchanger<String> exchanger= new Exchanger<>();
		ExchangerB b = new ExchangerB(exchanger);
		ExchangerA a = new ExchangerA(exchanger);
		a.start();
		b.start();
	}
	
}
