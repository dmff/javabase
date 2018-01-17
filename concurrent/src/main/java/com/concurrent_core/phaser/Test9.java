package com.concurrent_core.phaser;

import java.util.Date;
import java.util.concurrent.Phaser;

/**
 * 控制phaser的运行时机
 * @author dmf
 *
 */
public class Test9 {

	private Phaser phaser;

	public Test9(Phaser phaser) {
		super();
		this.phaser = phaser;
	}
	
	public void run(){
		System.out.println(Thread.currentThread().getName()+"A1 begin="+new Date(System.currentTimeMillis()));
		
		phaser.arriveAndAwaitAdvance();
		
		System.out.println(Thread.currentThread().getName()+"A1 end="+new Date(System.currentTimeMillis()));
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Phaser phaser = new Phaser(3);
		Test9 test9 = new Test9(phaser);
		
		phaser.register();
		
		for(int i=0;i<3;i++){
			Thread thread = new Thread(test9::run,"thread-A");
			thread.start();
		}
		
		Thread.sleep(3000);
		
		//通过注册和删除来控制phaser的运行时机
		phaser.arriveAndDeregister();
		
	}
}
