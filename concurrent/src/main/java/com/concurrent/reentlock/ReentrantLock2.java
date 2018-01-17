package com.concurrent.reentlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock用于替代synchronized
 * 使用reentrantlock
 * @author dmf
 *
 */
public class ReentrantLock2 {

	Lock lock = new ReentrantLock();
	
	 void m1() {
		lock.lock();
		try {
			for(int i=0; i<10; i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(i);
			}		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	 void m2(){
		lock.lock();
		System.out.println("m2 ...");
		lock.unlock();
	}
	
	public static void main(String[] args) {
		ReentrantLock2 r1 = new ReentrantLock2();
		
		new Thread(r1::m1).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(r1::m2).start();
	}
}
