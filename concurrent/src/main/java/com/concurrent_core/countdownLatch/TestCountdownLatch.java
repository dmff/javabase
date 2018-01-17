package com.concurrent_core.countdownLatch;

import java.util.concurrent.CountDownLatch;

public class TestCountdownLatch {

	private CountDownLatch downLatch = new CountDownLatch(1);
	
	public void run(){
		
		try {
			System.out.println("A");
			downLatch.await();
			System.out.println("B");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void down(){
		System.out.println("down");
		downLatch.countDown();
	}
	
	public static void main(String[] args) throws InterruptedException {
		TestCountdownLatch latch = new TestCountdownLatch();
		Thread thread1 = new Thread(latch::run,"threadLatch");
		thread1.start();
		
		Thread.sleep(3000);
		latch.down();
	}
}
