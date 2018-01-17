package com.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestMutext {

	Lock lock = new ReentrantLock();
	//Lock lock =  new Mutex();
	 void m1() {
		lock.lock();
		try {
			for(int i=0; i<10; i++) {
				//TimeUnit.SECONDS.sleep(1);
				System.out.println(i);
				Thread.sleep(100);
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
		TestMutext r1 = new TestMutext();
		
		new Thread(r1::m1).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(r1::m2).start();
	}

}
