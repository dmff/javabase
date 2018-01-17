package com.concurrent.reentlock;

import java.util.concurrent.TimeUnit;

/**
 * reentrantlock用于替代synchronized
 * m1和m2方法主要是复习synchronized的使用
 * @author dmf
 *
 */
public class ReentrantLock1 {

	synchronized void m1() {
		for(int i=0; i<10; i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}	
	}
	
	synchronized void m2(){
		System.out.println("m2 ...");
	}
	
	public static void main(String[] args) {
		ReentrantLock1 r1 = new ReentrantLock1();
		
		new Thread(r1::m1).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(r1::m2).start();
	}
}
