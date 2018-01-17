package com.concurrent.synchroniz;

/**
 * 同步和非同步方法是否可以同时调用？
 * 答案是可以的，非同步并不需要拿到锁，是可以直接访问的
 * @author dmf
 *
 */
public class T7 {
	
	public synchronized void m1(){
		System.out.println(Thread.currentThread().getName()+" m1 start...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName()+" m1 end...");
	}
	
	public void m2(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName()+" m2");
	}
	
	public static void main(String[] args) {
		T7 t7 = new T7();
		//new了2个线程，分别执行执行m1，m2方法
		/*new Thread(()->t7.m1(),"t1").start();
		new Thread(()->t7.m2(),"t2").start();*/
		
		new Thread(t7::m1, "t1").start();
		new Thread(t7::m2, "t2").start();
	}
}
