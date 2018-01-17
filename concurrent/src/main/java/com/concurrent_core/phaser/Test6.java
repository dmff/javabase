package com.concurrent_core.phaser;

import java.util.Date;
import java.util.concurrent.Phaser;

/**
 * 测试已经被使用的同行者的个数和未被使用的同行者格式
 * @author dmf
 *
 */
public class Test6 {

	private Phaser phaser;

	public Test6(Phaser phaser) {
		super();
		this.phaser = phaser;
	}
	
	public void run(){
		System.out.println(Thread.currentThread().getName()+"A1 begin="+new Date(System.currentTimeMillis()));
		phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName()+"A1 end="+new Date(System.currentTimeMillis()));
	}
	
	public static void main(String[] args) throws InterruptedException {
		Phaser phaser = new Phaser(7);
		Test6 test6 = new Test6(phaser);
		
		for(int i=0;i<5;i++){
			Thread thread = new Thread(test6::run,"thread-"+i);
			thread.start();
		}
		
		Thread.sleep(2000);
		System.out.println("已到达: "+phaser.getArrivedParties());
		System.out.println("未到达: "+phaser.getUnarrivedParties());
		
		
	}
}
