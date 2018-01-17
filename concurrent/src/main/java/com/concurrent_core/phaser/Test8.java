package com.concurrent_core.phaser;

import java.util.Date;
import java.util.concurrent.Phaser;

public class Test8 {

	private Phaser phaser;

	public Test8(Phaser phaser) {
		super();
		this.phaser = phaser;
	}
	
	public void run(){
		System.out.println(Thread.currentThread().getName()+"A1 begin="+new Date(System.currentTimeMillis()));
		
		//phaser.arriveAndAwaitAdvance();  //不可中断
		//phaser.awaitAdvanceInterruptibly(0);  //可中断，会抛出异常
		phaser.awaitAdvance(0);  //不可中断的
		
		System.out.println(Thread.currentThread().getName()+"A1 end="+new Date(System.currentTimeMillis()));
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Phaser phaser = new Phaser(3);
		Test8 test8 = new Test8(phaser);
		
		Thread thread = new Thread(test8::run,"thread-A");
		thread.start();
		
		Thread.sleep(5000);
		
		thread.interrupt();
		System.out.println("中断了");
	}
}
