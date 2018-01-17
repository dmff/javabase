package com.concurrent_core.scheduled;

public class Runnable2 implements Runnable {

	@Override
	public void run() {

		System.out.println("runnable2 begin " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

		System.out.println("runnable2 end " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

	}

}
