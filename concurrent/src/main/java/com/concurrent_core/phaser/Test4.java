package com.concurrent_core.phaser;

import java.util.concurrent.Phaser;

/**
 * 测试getPhaser，获取已经到达第几个屏障(到达屏障次数)
 * @author dmf
 *
 */
public class Test4 {

	private Phaser phaser;

	public Test4(Phaser phaser) {
		super();
		this.phaser = phaser;
	}
	
	public void run(){
		System.out.println("A begin");
		phaser.arriveAndAwaitAdvance();
		System.out.println("A end phaer value = "+phaser.getPhase());
		
		System.out.println("A begin");
		phaser.arriveAndAwaitAdvance();
		System.out.println("A end phaer value = "+phaser.getPhase());
		
		System.out.println("A begin");
		phaser.arriveAndAwaitAdvance();
		System.out.println("A end phaer value = "+phaser.getPhase());
		
		System.out.println("A begin");
		phaser.arriveAndAwaitAdvance();
		System.out.println("A end phaer value = "+phaser.getPhase());
	}
	
	public static void main(String[] args) {
		Phaser phaser = new Phaser(1);
		Test4 test4 = new Test4(phaser);
		
		Thread thread = new Thread(test4::run);
		thread.start();
	}
}
