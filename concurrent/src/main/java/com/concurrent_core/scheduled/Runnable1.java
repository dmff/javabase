package com.concurrent_core.scheduled;

public class Runnable1 implements Runnable{

	@Override
	public void run() {
		try {
			System.out.println("runnable1 begin " + Thread.currentThread().getName()+" "+System.currentTimeMillis());
			Thread.sleep(4000);	
			System.out.println("runnable1 end " + Thread.currentThread().getName()+" "+System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
