package com.concurrent_core.phaser;

import java.util.concurrent.Phaser;

/**
 * 打印的工具类，测试phaser的arriveAndAwaitAdvance方法
 * phaser可以设置多重屏障，此例设置了两重屏障，相当于cyclicbarrier的作用
 * @author dmf
 *
 */
public class PrintTool2 {

	public Phaser phaser;

	public PrintTool2(Phaser phaser) {
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
			System.out.println(Thread.currentThread().getName()+" A1 begin = "+System.currentTimeMillis());
			Thread.sleep(5000);
			phaser.arriveAndAwaitAdvance();   //相当于countdownlatch的await方法
			System.out.println(Thread.currentThread().getName()+" A1 end = "+System.currentTimeMillis());
			
			System.out.println(Thread.currentThread().getName()+" A2 begin = "+System.currentTimeMillis());
			Thread.sleep(5000);
			phaser.arriveAndAwaitAdvance();   //相当于countdownlatch的await方法
			System.out.println(Thread.currentThread().getName()+" A2 end = "+System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Phaser phaser = new Phaser(3);
		PrintTool2 tool = new PrintTool2(phaser);
		
		Thread threadA = new Thread(tool::method1, "threadA");
		threadA.start();
		
		Thread threadB = new Thread(tool::method1, "threadB");
		threadB.start();
		
		Thread threadC = new Thread(tool::method2, "threadC");
		threadC.start();
	}
}
