package com.concurrent_core.phaser;

import java.util.concurrent.Phaser;

/**
 * 测试arriverAndDeregister：此方法的作用是当前线程会给你退出比赛，并且使得parties值减1
 * @author dmf
 *
 */
public class PrintTool3 {

	public Phaser phaser;

	public PrintTool3(Phaser phaser) {
		super();
		this.phaser = phaser;
	}
	
	public void method1(){
		System.out.println(Thread.currentThread().getName()+" A1 begin = "+System.currentTimeMillis());
		phaser.arriveAndAwaitAdvance();   //相当于countdownlatch的await方法或者cyclicbarrier的await方法
		System.out.println(Thread.currentThread().getName()+" A1 end = "+System.currentTimeMillis());
		
		System.out.println(Thread.currentThread().getName()+" A2 begin = "+System.currentTimeMillis());
		phaser.arriveAndAwaitAdvance();   //相当于countdownlatch的await方法
		System.out.println(Thread.currentThread().getName()+" A2 end = "+System.currentTimeMillis());
	}
	
	public void method2(){
		try {
			System.out.println(Thread.currentThread().getName()+" A1 begin = "+System.currentTimeMillis()+" 注册的同行者为:"+phaser.getRegisteredParties());
			Thread.sleep(5000);
			phaser.arriveAndDeregister();
			System.out.println(Thread.currentThread().getName()+" A1 end = "+System.currentTimeMillis()+" 注册的同行者为:"+phaser.getRegisteredParties());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Phaser phaser = new Phaser(3);
		PrintTool3 tool = new PrintTool3(phaser);
		
		Thread threadA = new Thread(tool::method1, "threadA");
		threadA.start();
		
		Thread threadB = new Thread(tool::method1, "threadB");
		threadB.start();
		
		Thread threadC = new Thread(tool::method2, "threadC");
		threadC.start();
	}
}
