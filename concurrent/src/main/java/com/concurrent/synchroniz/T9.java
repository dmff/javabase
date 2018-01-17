package com.concurrent.synchroniz;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁
 * 是不会造成死锁的，说白了就是可以重入的
 * 继承中有可能发生的情形，子类调用父类的同步方法
 * @author dmf
 *
 */
public class T9 {
	
	public synchronized void m1(){
		System.out.println(Thread.currentThread().getName()+" m1 start...");
		
		m2();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	public synchronized void m2(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("m2");
	}
	
	public static void main(String[] args) {
		new Thread(()->new T9().m1(), "T9").start();
	}
}
