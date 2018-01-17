package com.concurrent_core.phaser;

import java.util.concurrent.Phaser;

/**
 * 测试二：当计数不足时，线程呈现阻塞状态，说白了和cyclicbarrier一样，只有满足所有的同行者到达屏障点，才能继续往下执行
 * 否则会一直阻塞
 * @author dmf
 *
 */
public class PrintTool {

	public Phaser phaser;

	public PrintTool(Phaser phaser) {
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
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Phaser phaser = new Phaser(3);
		PrintTool tool = new PrintTool(phaser);
		
		Thread threadA = new Thread(tool::method1, "threadA");
		threadA.start();
		
		Thread threadB = new Thread(tool::method1, "threadB");
		threadB.start();
		
		Thread threadC = new Thread(tool::method2, "threadC");
		threadC.start();
	}
}
