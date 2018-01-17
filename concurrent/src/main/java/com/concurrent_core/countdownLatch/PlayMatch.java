package com.concurrent_core.countdownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 测试裁判在等待所有的运动员到来,准备完毕后，在开始比赛；使用countdownlatch
 * 只要门闩一打开，所有的线程都是同时执行的(并不会，可能裁判员没有等所有运动员准备完就开枪了)
 * @author dmf
 *
 */
public class PlayMatch {

	private CountDownLatch down;

	public PlayMatch(CountDownLatch down) {
		super();
		this.down = down;
	}
	
	public void run() {
		System.out.println(System.currentTimeMillis()+"准备");
		down.countDown();
		System.out.println(System.currentTimeMillis()+"结束");
	}
	
	public void down(){
		System.out.println("开始");
		down.countDown();
	}
	
	public static void main(String[] args) throws InterruptedException  {
		CountDownLatch maxRunner = new CountDownLatch(10);
		PlayMatch wait = new PlayMatch(maxRunner);
		
		for(int i=0;i<10;i++){
			Thread thread = new Thread(wait::run,"thread"+(i+1));
			thread.start();
		}
		
		Thread.sleep(2000);
		wait.down();
	}
}
