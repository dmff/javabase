package com.concurrent_core.phaser;

import java.util.Date;
import java.util.concurrent.Phaser;

/**
 * 测试onadvance方法：每次都过新的屏障的时候会被调用
 * 出现了A只会等待一次5秒钟，说明屏障失效了
 * @author dmf
 *
 */
public class Test5 {

	private Phaser phaser;

	public Test5(Phaser phaser) {
		super();
		this.phaser = phaser;
	}
	
	public void method() {
		try {
			System.out.println("A begin threadname="+Thread.currentThread().getName()+":"+new Date(System.currentTimeMillis()));
			if (Thread.currentThread().getName().equals("threadB")) {
				Thread.sleep(5000);
			}
			
			phaser.arriveAndAwaitAdvance();
			System.out.println("A end threadname="+Thread.currentThread().getName()+":"+new Date(System.currentTimeMillis()));
			
			//-------------------------------
			System.out.println("B begin threadname="+Thread.currentThread().getName()+":"+new Date(System.currentTimeMillis()));
			if (Thread.currentThread().getName().equals("threadB")) {
				Thread.sleep(5000);
			}
			
			phaser.arriveAndAwaitAdvance();
			System.out.println("B end threadname="+Thread.currentThread().getName()+":"+new Date(System.currentTimeMillis()));
			
			//------------------------------------
			System.out.println("C begin threadname="+Thread.currentThread().getName()+":"+new Date(System.currentTimeMillis()));
			if (Thread.currentThread().getName().equals("threadB")) {
				Thread.sleep(5000);
			}
			
			phaser.arriveAndAwaitAdvance();
			System.out.println("C end threadname="+Thread.currentThread().getName()+":"+new Date(System.currentTimeMillis()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Phaser phaser = new Phaser(2){

			@Override
			protected boolean onAdvance(int phase, int registeredParties) {
				System.out.println("onadvance被调用....");  //每次都过新的屏障的时候会被调用
				//返回true不等待，phaser失效
				//返回false，phaser继续工作
				//return true;
				return false;
			}
			
		};
		
		Test5 test5 = new Test5(phaser);
		
		Thread threadA = new Thread(test5::method, "threadA");
		threadA.start();
		
		Thread threadB = new Thread(test5::method, "threadB");
		threadB.start();
	}
}
