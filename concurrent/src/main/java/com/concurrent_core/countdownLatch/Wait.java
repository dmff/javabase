package com.concurrent_core.countdownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 测试裁判在等待所有的运动员到来；使用countdownlatch
 * 只要门闩一打开，所有的线程都是同时执行的
 * @author dmf
 *
 */
public class Wait {

	private CountDownLatch maxRunner;

	public Wait(CountDownLatch maxRunner) {
		super();
		this.maxRunner = maxRunner;
	}
	
	public void run(){
		try {
			Thread.sleep(2000);
			maxRunner.countDown();
			System.out.println(System.currentTimeMillis()+"回来了");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch maxRunner = new CountDownLatch(10);
		Wait wait = new Wait(maxRunner);
		
		for(int i=0;i<10;i++){
			Thread thread = new Thread(wait::run,"thread"+(i+1));
			thread.start();
		}
		
		maxRunner.await();
		System.out.println(System.currentTimeMillis()+"都回来了");
	}
}
